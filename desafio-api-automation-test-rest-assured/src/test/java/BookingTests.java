import Entities.Booking;
import Entities.BookingDates;
import Entities.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//testmethodorder tinha colocado pois eu estava fazendo os testes de forma dependente, porém depois consegui fazer os testes de forma independentes
@TestMethodOrder(OrderAnnotation.class)
public class BookingTests {
    public static Faker faker;
    private static RequestSpecification request;
    private static Booking booking;
    private static BookingDates bookingDates;
    private static User user;

    @BeforeAll
    public static void Setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        faker = new Faker();
        user = new User(faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().safeEmailAddress(),
                faker.internet().password(8, 10),
                faker.phoneNumber().toString());

        //adicionado data aleatória e com o formato proposto na documentação 'ano-mês-dia'
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Faker faker = new Faker();
        String checkin = sdf.format(faker.date().birthday());
        String checkout = sdf.format(faker.date().birthday());

        bookingDates = new BookingDates(checkin, checkout);
        booking = new Booking(user.getFirstName(), user.getLastName(),
                (float)faker.number().randomDouble(2, 50, 100000),
                true,bookingDates,
                "");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }

    @BeforeEach
    void setRequest() {
        request = given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .contentType(ContentType.JSON)
                .auth().basic("admin", "password123");

    }

    @Test
    @Order(value = 1)
    public void getAllBookingsById_returnOk(){
        Response response = request
                .when()
                .get("/booking")
                .then()
                .extract()
                .response();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(value = 2)
    public void getAllBookingByUserFirstName_BookingExists_returnOk() {
        request
                .when()
                    .queryParam("firstName", "Julia")
                    .get("/booking")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                .and()
                .body("results", hasSize(greaterThan(0)));
    }

    @Test
    @Order(value = 3)
    public void CreateBooking_WithValidData_returnOk() {
        //adicionado Response response
        Response response = RestAssured.given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");
        //adicionado response
        response.then()
                .body(matchesJsonSchemaInClasspath("createBookingRequestSchema.json"))
                .and()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON).and().time(lessThan(5000L));
    }

    @Test
    @Order(value = 4)
    public void UpdateBooking_WithValidData_returnOk() {

        Integer bookingId = this.createBooking();

        bookingDates = new BookingDates("2022-11-11", "2022-12-30");

        Booking booking = new Booking(
                faker.name().firstName(),
                faker.name().lastName(),
                333F,
                true,
                bookingDates,
                "Dinner");

        given()
                .contentType(ContentType.JSON)
                .body(booking)
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .put("/booking/"+ String.valueOf(bookingId) )
                .then()
                .statusCode(200)
                .body(
                        "firstname", is(booking.getFirstname()),
                        "lastname", is(booking.getLastname()));
    }

    @Test
    @Order(value = 5)
    public void PartialUpdateBooking_WithValidData_returnOk() {

        Integer bookingId = this.createBooking();

        String firstName = "Michelangelo";
        String lastName = "Marques";

        String booking = "{\"firstname\":\"" + firstName + "\",\"lastname\":\"" + lastName + "\"}";

        given()
                .contentType(ContentType.JSON)
                .body(booking)
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .patch("/booking/"+ String.valueOf(bookingId) )
                .then()
                .statusCode(200)
                .body(
                        "firstname", is(firstName),
                   "lastname", is(lastName));
    }

    @Test
    @Order(value = 6)
    public void DeleteBooking_WithValidData_returnOk() {

        Integer bookingId = this.createBooking();

        given()
                .contentType(ContentType.JSON)
                .body(booking)
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("booking/" + String.valueOf(bookingId))
                .then()
                .statusCode(201);

    }

    @Test
    @Order(value = 7)
    public void HealthCheckPing_returnOk() {
        given()
                .when()
                .get("/ping")
                .then()
                .statusCode(201);
    }

    //serve para criar bookings durante os testes de update, partial update e delete
    private Integer createBooking(){

        Response response = RestAssured.given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");

        ResponseBody responseBody = response.getBody();

        String bodyAsString = responseBody.asString();

        return response.jsonPath().getInt("bookingid");
    }
}


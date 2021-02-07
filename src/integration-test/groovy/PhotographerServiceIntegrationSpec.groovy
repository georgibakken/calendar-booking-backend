package calendarbooking

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration

import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@SuppressWarnings('MethodName')
@Rollback
@Integration
class PhotographerServiceIntegrationSpec extends Specification {

    @Autowired PhotographerService service

    def setup() {
        Photographer.saveAll(
                new Photographer(name: "Otto Crawford")
                        .addToBookings(new Booking(
                                starts: "2020-11-25T08:30:00Z",
                                ends: "2020-11-25T09:30:00Z"))
                        .addToAvailabilities(new Availability(
                                starts: "2020-11-25T08:00:00Z",
                                ends: "2020-11-25T16:00:00Z")),
                new Photographer(name: "Jens Mills")
                        .addToBookings(new Booking(
                                starts: "2020-11-25T15:00:00Z",
                                ends: "2020-11-25T16:00:00Z"))
                        .addToAvailabilities(new Availability(
                                starts: "2020-11-25T08:00:00Z",
                                ends: "2020-11-25T09:00:00Z"))
                        .addToAvailabilities(new Availability(
                                starts: "2020-11-25T13:00:00Z",
                                ends: "2020-11-25T16:00:00Z"))
        )
    }

    void "find correct timeSlots for an available date"() {
        given:
        def booking = new Booking(durationInMinutes: 90).save()
        LocalDate date = LocalDate.parse("2020-11-25")

        when:
        def availablePhotographers = service.findTimeSlot(date, booking.durationInMinutes)

        then:
        availablePhotographers[0].photographer.name == "Otto Crawford"
        availablePhotographers[0].timeSlot.starts == "2020-11-25T09:30:00Z"
        availablePhotographers[0].timeSlot.ends == "2020-11-25T11:00:00Z"
        availablePhotographers[1].photographer.name == "Jens Mills"
        availablePhotographers[1].timeSlot.starts == "2020-11-25T13:00:00Z"
        availablePhotographers[1].timeSlot.ends == "2020-11-25T14:30:00Z"
    }

    void "no timeslots for non-available date"() {
        given:
        def booking = new Booking(durationInMinutes: 90).save()
        LocalDate date = LocalDate.parse("2020-12-12")

        when:
        def availablePhotographers = service.findTimeSlot(date, booking.durationInMinutes)

        then:
        availablePhotographers.isEmpty()

    }


}
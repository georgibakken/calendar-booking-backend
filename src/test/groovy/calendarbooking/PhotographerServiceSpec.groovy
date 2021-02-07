package calendarbooking

import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest

import java.time.Instant

class PhotographerServiceSpec extends HibernateSpec implements ServiceUnitTest<PhotographerService>{

    List<Class> getDomainClasses() { [Photographer, TimeSlot] }
    def 'test find all photographers'() {
        when: 'photographers already stored in db'
        Photographer.saveAll(
                new Photographer(name: 'John'),
                new Photographer(name: 'Peter')
        )

        then:
        Photographer.count() == 2

        when: 'service is called to search'
        List<Photographer> photographers = service.listPhotographers()

        then:
        photographers.size() == 2
        photographers[0].name == 'John'
        photographers[1].name == 'Peter'
    }

    def 'test calculate end date'(){
        when:
        Instant start = Instant.parse("2020-11-25T08:30:00Z")
        Integer duration = 120
        Instant end = service.calculateBookingEnds(start, duration)

        then:
        end.toString() == "2020-11-25T10:30:00Z"

    }

    def 'isTimeSlotAlreadyTaken works correctly'() {
        when: 'timeslot is taken'
        TimeSlot timeSlot = new TimeSlot(starts: Instant.parse("2020-11-25T08:30:00Z"), ends: Instant.parse("2020-11-25T09:30:00Z"))
        Booking booking = new Booking(starts: Instant.parse("2020-11-25T09:30:00Z"), ends: Instant.parse("2020-11-25T10:30:00Z"))

        then:
        service.isTimeSlotAlreadyTaken(timeSlot, booking) == true

        when: 'timeslot is not taken'
        TimeSlot timeSlot2 = new TimeSlot(starts: Instant.parse("2020-11-25T08:30:00Z"), ends: Instant.parse("2020-11-25T09:30:00Z"))
        Booking booking2 = new Booking(starts: Instant.parse("2020-11-25T10:30:00Z"), ends: Instant.parse("2020-11-25T11:30:00Z"))

        then:
        service.isTimeSlotAlreadyTaken(timeSlot2, booking2) == false

        when: 'timeslot is valid with 1 minute leeway'
        TimeSlot timeSlotLeeway = new TimeSlot(starts: Instant.parse("2020-11-25T08:30:00Z"), ends: Instant.parse("2020-11-25T09:30:00Z"))
        Booking bookingLeeway = new Booking(starts: Instant.parse("2020-11-25T09:30:00Z"), ends: Instant.parse("2020-11-25T11:30:00Z"))

        then:
        service.isTimeSlotAlreadyTaken(timeSlotLeeway, bookingLeeway) == true

    }
}

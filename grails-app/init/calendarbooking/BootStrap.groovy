package calendarbooking

import java.time.Instant

class BootStrap {


    def init = { servletContext ->

        def p = new Photographer(name: "Otto Crawford")
                .addToBookings(new Booking(
                        starts: "2020-11-25T08:30:00Z",
                        ends: "2020-11-25T09:30:00Z"))
                .addToAvailabilities(new Availability(
                        starts: "2020-11-25T08:00:00Z",
                        ends: "2020-11-25T16:00:00Z"))
                .save()
        log.info("Photographer 1: ${p}")

        def p2 = new Photographer(name: "Jens Mills")
                .addToBookings(new Booking(
                        starts: "2020-11-25T15:00:00Z",
                        ends: "2020-11-25T16:00:00Z"))
                .addToAvailabilities(new Availability(
                        starts: "2020-11-25T08:00:00Z",
                        ends: "2020-11-25T09:00:00Z"))
                .addToAvailabilities(new Availability(
                        starts: "2020-11-25T13:00:00Z",
                        ends: "2020-11-25T16:00:00Z"))
                .save()
        log.info("Photographer 2: ${p2}")

        def booking = new Booking(id: 5, durationInMinutes: 90).save()
        log.info("Booking 1: ${booking}")
    }
    def destroy = {
    }
}

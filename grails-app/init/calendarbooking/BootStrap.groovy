package calendarbooking

import java.time.Instant

class BootStrap {


    def init = { servletContext ->

//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

        def p =new Photographer(name: "Otto Crawford")
                .addToBookings(new Booking(
                        starts: Instant.parse("2020-11-25T08:30:00Z"),
                        ends: Instant.parse("2020-11-25T09:30:00Z")))
                .addToAvailabilities(new Availability(
                        starts: Instant.parse("2020-11-25T08:00:00Z"),
                        ends: Instant.parse("2020-11-25T16:00:00Z")))
            .save()
        log.info("Photographer 1: ${p}")

        def booking = new Booking(durationInMinutes: "120").save()
        log.info("Booking 1: ${booking}")


//        p = new Photographer(name: "Fred Jackson")
//        p.save()


    }
    def destroy = {
    }
}

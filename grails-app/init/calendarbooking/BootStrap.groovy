package calendarbooking

class BootStrap {

    def init = { servletContext ->

        def a = new Photographer(name: "Fred Jackson").save()
        log.info("Photographer 1: ${a.name}")


        def p = new Photographer(name: "Otto Crawford")
            .addToBookings(new Booking(
                        durationInMinutes: "90",
                        starts: new Date(),
                        ends: new Date()))
            .addToAvailabilities(
                    new Availability(
                        starts: "2020-11-25T08:00:00.000Z",
                        ends: "2020-11-25T16:00:00.000Z"))
            .save()
        log.info("Photographer 2: ${p}")


//        p = new Photographer(name: "Fred Jackson")
//        p.save()


    }
    def destroy = {
    }
}

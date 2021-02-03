package calendarbooking

import java.time.Instant

class Booking {

    static belongsTo = [photographer: Photographer]
    Integer durationInMinutes
    Instant starts
    Instant ends

    static constraints = {
        durationInMinutes nullable: true
        starts nullable: true
        ends nullable: true
        photographer nullable: true
    }
}

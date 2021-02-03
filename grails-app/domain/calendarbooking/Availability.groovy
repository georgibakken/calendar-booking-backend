package calendarbooking

import java.time.Instant

class Availability {

    static belongsTo = [photographer: Photographer]
    Instant starts
    Instant ends

    static constraints = {
    }
}

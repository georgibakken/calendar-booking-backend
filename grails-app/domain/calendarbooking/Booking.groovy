package calendarbooking

import grails.databinding.BindUsing

import java.time.Instant

class Booking {

    static belongsTo = [photographer: Photographer]
    Integer durationInMinutes

    @BindUsing({
        obj, source -> Instant.parse((String) source['starts'])
    })
    Instant starts

    @BindUsing({
        obj, source -> Instant.parse((String) source['ends'])
    })
    Instant ends

    static constraints = {
        durationInMinutes nullable: true
        starts nullable: true
        ends nullable: true
        photographer nullable: true
    }
}

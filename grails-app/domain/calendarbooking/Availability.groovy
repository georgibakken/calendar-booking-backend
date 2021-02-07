package calendarbooking

import grails.databinding.BindUsing

import java.time.Instant

class Availability {

    static belongsTo = [photographer: Photographer]

    @BindUsing({
        obj, source -> Instant.parse((String) source['starts'])
    })
    Instant starts

    @BindUsing({
        obj, source -> Instant.parse((String) source['ends'])
    })
    Instant ends

    static constraints = {
    }
}

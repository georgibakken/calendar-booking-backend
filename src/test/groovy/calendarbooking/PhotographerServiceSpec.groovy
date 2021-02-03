package calendarbooking

import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

import java.time.Instant

class PhotographerServiceSpec extends HibernateSpec implements ServiceUnitTest<PhotographerService>{

    List<Class> getDomainClasses() { [Photographer] }
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
}

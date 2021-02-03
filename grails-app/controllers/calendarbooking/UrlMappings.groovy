package calendarbooking

class UrlMappings {

    static mappings = {

//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }

        get "/api/photographers"(controller: 'photographer')
        get "/api/photographers/available"(controller: 'photographer', action: 'findAvailablePhotographers')



        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

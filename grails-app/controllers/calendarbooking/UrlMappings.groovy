package calendarbooking

class UrlMappings {

    static mappings = {

//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }

        get "/api/photographers"(controller: 'photographer')


        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

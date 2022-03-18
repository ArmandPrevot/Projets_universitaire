package td_1

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        ///USERS
        get "/api/v1/users"(controller : "user", action : 'getUsersList')
        get "/api/v1/user/$id"(controller: "user", action: 'findOneById')
        put "/api/v1/user/$id"(controller: "user", action: 'updateById')
        delete "/api/v1/user/$id"(controller: "user", action: 'deleteById')
        post "/api/v1/user/"(controller: "user", action: 'create')

        ///ANNONCES
        get "/api/v1/annonces"(controller: "annonce", action: 'findAll')
        get "/api/v1/annonce/owner/$id"(controller: "annonce", action: 'findByOwner')
        get "/api/v1/annonce/$id"(controller: "annonce", action: 'findOneById')
        put "/api/v1/annonce/$id"(controller: "annonce", action: 'updateById')
        delete "/api/v1/annonce/$id"(controller: "annonce", action: 'deleteById')
        post "/api/v1/annonce/"(controller: "annonce", action: 'create')

        ///ILLUSTRATIONS
        get "/api/v1/illustrations"(controller: "illustration", action: 'findAll')
        get "/api/v1/illustration/$id"(controller: "illustration", action: 'findOneById')
        delete "/api/v1/illustration/$id"(controller: "illustration", action: 'deleteOneById')
        post "/api/v1/illustration"(controller: "illustration", action: 'create')

    }
}

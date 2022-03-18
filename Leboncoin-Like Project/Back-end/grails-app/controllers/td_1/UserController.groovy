package td_1

import org.springframework.security.access.annotation.Secured

@Secured("ROLE_ADMIN")
class UserController {

    UserService userService;
    static allowedMethods = [getUsersList: "GET", findOneById: "GET", updateById: "PUT", deleteById: 'DELETE', create: 'POST']

    def getUsersList() {
        render(template: '/user/users', model: [users:User.getAll()]);
    }

    def create() {
        User user = userService.createUserFromBody(request.JSON);
        render(template: '/user/user', model: [user:user]);
    }

    def findOneById() {
        try {
            int id = Integer.parseInt(params.id, 10);
            User user = userService.findOneById(id);

            if (!user) throw new Exception("User not found");

            render(template: '/user/user', model: [user:user]);
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }

    def updateById() {
        try {
            int id = Integer.parseInt(params.id, 10);
            User user = userService.findOneById(id);

            if (!user) throw new Exception("User not found");

            userService.updateUser(user, request.JSON);

            render(template: '/user/user', model: [user:user]);
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }

    def deleteById () {
        try {
            int id = Integer.parseInt(params.id, 10);
            User user = userService.findOneById(id);

            if (!user) throw new Exception("User not found");
            userService.deleteOne(user);

            render(message: 'User deleted successfully');
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }
}

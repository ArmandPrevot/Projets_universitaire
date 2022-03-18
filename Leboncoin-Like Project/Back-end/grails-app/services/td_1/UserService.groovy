package td_1

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def findOneById(id) {
        return User.findById(id);
    }

    def updateUser(User user, Object data) {
        user.properties = data;
        user.save();
    }

    def deleteOne(User user) {
        UserRole.removeAll(user);
        user.delete();
    }

    def createUserFromBody(body) {
        User user = new User(
                password: body.password,
                username: body.username,
                annonces: [],
        ).save();
        UserRole.create(user, Role.findByAuthority(body.role), true);

        return user;
    }
}

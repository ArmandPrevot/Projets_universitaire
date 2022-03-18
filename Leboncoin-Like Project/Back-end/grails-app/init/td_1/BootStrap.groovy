package td_1

class BootStrap {

    def init = { servletContext ->
        //5 users
        //5 annonces
        //3 pic / annonce

        Role roleAdmin = new Role(authority: 'ROLE_ADMIN').save();
        Role roleUser = new Role(authority: 'ROLE_USER').save();

        for (int i = 0; i < 5; i++) {
            ArrayList<Annonce> annonces = new ArrayList<>();
            for(int j = 0; j < 5; j++) {
                ArrayList<Illustration> illustrations = new ArrayList<>();
                for(int k = 0; k < 3; k++) {
                    illustrations.add(
                            new Illustration(filename: ('filename_' + k)),
                    );
                }
                annonces.add(new Annonce(
                        title: ('title_' + j),
                        description: 'lkklklkl',
                        price: 12,
                        images: [illustrations.get(0), illustrations.get(1), illustrations.get(2)],
                ));
            }

            User.withTransaction {
                User user = new User(
                        username: ('user' + i),
                        password: 'passwd1234',
                        annonces: [
                                annonces.get(0),
                                annonces.get(1),
                                annonces.get(2),
                                annonces.get(3),
                                annonces.get(4),
                        ]
                ).save();
                UserRole.create(user, i == 0 ? roleAdmin : roleUser, true);
            }
        }

        User.withTransaction {
            User user = new User(
                    username: ('user_test'),
                    password: 'passwd1234',
            ).save();
            UserRole.create(user, roleUser, true);
        }
    }
    def destroy = {
    }
}

package td_1

import grails.rest.Resource

class Annonce {

    String title;
    String description;
    float price;
    Date dateCreated;
    Date lastUpdated;

    static hasMany = [images: Illustration];
    static belongsTo = [author: User]; //annonce => côté faible, car si on del user on doit del son annonce

    static constraints = {
        title nullable: false, blank: false, size: 4..50;
        description nullable: false, blank: true;
        price nullable: false, min: 0F;
        images maxSize: 5;
    }
}

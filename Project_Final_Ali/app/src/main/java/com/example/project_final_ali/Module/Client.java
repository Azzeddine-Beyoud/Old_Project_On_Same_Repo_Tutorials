package com.example.project_final_ali.Module;

public class Client {

    private int id;
    private String nameClient;
    private String pictureUri;
    private String phone  ;
    private String timeStamp;
    private String DocumentID ;
    private int reference ;

    public Client() {
    }

    public Client(int id , String nameClient, String pictureUri, String phone, int reference ) {
        this.id = id;
        this.nameClient = nameClient;
        this.pictureUri = pictureUri;
        this.phone = phone;
        this.reference = reference;
    }
    public Client(String nameClient, String pictureUri, String phone, int reference ,String city ) {
        this.nameClient = nameClient;
        this.pictureUri = pictureUri;
        this.phone = phone;
        this.reference = reference;

    }

    public Client(String nameClient, String phone) {
        this.nameClient = nameClient;
        this.phone = phone;
    }


    public Client(int id, String nameClient, String pictureUri, String phone, String timeStamp, int reference) {
        this.id = id;
        this.nameClient = nameClient;
        this.pictureUri = pictureUri;
        this.phone = phone;
        this.timeStamp = timeStamp;
        this.reference = reference;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDocumentID() {
        return DocumentID;
    }
    public void setDocumentID(String documentID) {
        this.DocumentID = documentID;
    }


    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
    
    
    
    //    public Client( String imageUrl, String nameClient, String city,
//
//                  int phone, int price, int priority) {
//        super(nameClient, imageUrl);
//
//        this.nameClient = nameClient;
//        this.city = city;
//        this.phone = phone;
//        this.price = price;
//        this.priority = priority;
//    }

//    public Client(String nameClient, int phone, String city, int price, int priority) {
//
//        this.nameClient = nameClient;
//        this.phone = phone;
//        this.city = city;
//        this.price = price;
//        this.priority = priority;
//    }




//    public int getPriority() {
//        return priority;
//    }
//
//    public void setPriority(int priority) {
//        this.priority = priority;
//    }





//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }

//    public int getIdCommand() {
//        return idCommand;
//    }
//
//    public void setIdCommand(int idCommand) {
//        this.idCommand = idCommand;
//    }


//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }




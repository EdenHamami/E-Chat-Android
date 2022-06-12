//package com.example.echat;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class ContactsRepository {
//    private ContactDao dao;
//    private ContactListData contactListData;
//    private ContactAPI api;
//
//    public ContactsRepository(){
//    //    LocalDatabase db=LocalDatabase.getInstance();
//        //dao=db.ContactDao();
//        contactListData=new ContactListData();
//        api=new ContactAPI(contactListData,dao);
//    }
//    class ContactListData extends MutableLiveData<List<Contact>>{
//        public ContactListData(){
//            super();
//            setValue((new LinkedList<>()));
//        }
//        @Override
//        protected void onActive(){
//            super.onActive();
//            new Thread(()->{
//                contactListData.contactValue(dao.get());
//            }).start();
//        }
//    }
//    public LiveData<List<Contact>> getAll(){return contactListData;}
//    public void add(final Contact contact){api.add(contact);}
//    public void delete(final Contact contact){api.delete(contact);}
//    public void reload(){api.get();}
//}

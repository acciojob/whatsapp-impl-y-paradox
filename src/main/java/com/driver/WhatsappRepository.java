package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below-mentioned hashmaps or delete these and create your own.
    private HashMap<String, User> userMap;
    private HashMap<User, List<User>> adminMap;
    private HashMap<Group, List<User>> groupMap;
    private HashMap<Group, List<Message>> messageMap;
    private HashMap<Group, User> groupToAdmin;

    private int noOfGroup;
    private int noOfMessage;


    public WhatsappRepository(){
        this.userMap = new HashMap<>();
        this.groupToAdmin = new HashMap<>();
        this.messageMap = new HashMap<>();
        this.adminMap = new HashMap<>();
        this.groupMap = new HashMap<>();
        this.noOfGroup = 1;
        this.noOfMessage = 1;
    }

    public String createUser(String name, String mobile) throws Exception{
        if(!userMap.containsKey(mobile)){
            User user = new User();
            user.setName(name);
            user.setMobile(mobile);
            userMap.put(mobile, user);

            return "SUCCESS";
        }
        else{
            throw new Exception("User already exists");
        }
    }

    public Group createGroup(List<User> users){
        Group group = new Group();
        if(users.size() == 2){
            group.setName(users.get(1).getName());
            group.setNumberOfParticipants((2));
        }
        else{
            String groupName = "Group "+ noOfGroup;
            group.setName(groupName);
            group.setNumberOfParticipants(users.size());
            noOfGroup++;
        }

        groupMap.put(group,users);
        groupToAdmin.put(group,users.get(0));

        return group;
    }

    public int createMessage(String content){
        int id = noOfMessage;

        Message message = new Message();
        noOfMessage = noOfMessage +1;

        return id;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception{
        if(groupMap.containsKey(group)){
            List<User> users = groupMap.get(group);
            if(users.contains(sender)){
                List<Message> messages;

                if(messageMap.containsKey(group)){
                    messages = messageMap.get(group);
                }
                else{
                    messages = new ArrayList<>();
                }
                messages.add(message);
                messageMap.put(group, messages);
                return messages.size();
            }
            else{
                throw new Exception("You are not allowed to send message");
            }
        }
        else{
            throw new Exception("Group does not exist");
        }
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception{
        if(groupMap.containsKey(group)){
            User currAdmin = groupToAdmin.get(group);
            if(currAdmin.equals(approver)){
                List<User> users = groupMap.get(group);

                if(users.contains(user)) {
                    groupToAdmin.put(group, user);
                }
                else{
                    throw new Exception("User is not a participant");
                }
            }
            else{
                throw new Exception("Approver does not have rights");
            }
        }
        else{
            throw new Exception("Group does not exist");
        }
        return "SUCCESS";
    }

    public int removeUser(User user) throws Exception{
        return 0;
    }

    public String findMessage(Date start, Date end, int k) throws Exception{
        return "";
    }


}

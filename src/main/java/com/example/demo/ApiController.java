package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Controller
@RestController
public class ApiController {
    ArrayList<User> users = new ArrayList<User>();

    //404
    public class NotFoundException extends ResponseStatusException {
        public NotFoundException() {
            super(HttpStatus.NOT_FOUND, "not found");
        }
    }

    //400
    public class BadRequestException  extends ResponseStatusException {
        public BadRequestException() {
            super(HttpStatus.BAD_REQUEST, "bad request");
        }
    }

    //403
    public class OtherErrorException extends ResponseStatusException {
        public OtherErrorException() {
            super(HttpStatus.FORBIDDEN, "other");
        }
    }

    //409
    public class ConflictException extends ResponseStatusException {
        public ConflictException() {
            super(HttpStatus.CONFLICT, "conflict");
        }
    }


    @PostMapping("users")
    public void addUser(@RequestBody UserProv new_user) throws Exception
    {
        //throw new NotFoundException();
        String username = new_user.getUsername();
        for (User user : users)
        {
            if (Objects.equals(user.getUsername(), username))
                throw new ConflictException();
        }
        //return new_user.getPassword() + " " + new_user.getRepeatPassword();
        for (int i = 0; i < username.length(); i++)
        {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || c == '_' || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')))
                throw new BadRequestException();
        }
        if (!Objects.equals(new_user.getPassword(), new_user.getRepeatPassword()))
            throw new BadRequestException();
        users.add(new User(username, new_user.getPassword(), new_user.getAge()));
    }

    @GetMapping("users/{name}")
    public String getUser(@PathVariable("name") String name) throws Exception {
        for (User user : users)
        {
            if (Objects.equals(user.getUsername(), name))
            {
                return user.toString();
            }

        }

        throw new NotFoundException();
    }

    @GetMapping("users/SearchForAge/{age}")
    public String getUserAge(@PathVariable("age") Integer age) throws Exception {
        String ans = "";
        for (User user : users)
        {
            if (Math.abs(user.getAge() - age) <= 5)
            {
                ans += user.toString() + "\n";
            }

        }
        return ans;
    }

    @DeleteMapping("users/{name}")
    public void Del(@PathVariable("name") String name) throws Exception
    {
        if (name.length() < 5) {
            throw new OtherErrorException();
        }

        if (!name.substring(0, 5).equals("admin")) {
            throw new OtherErrorException();
        }
        for (User user : users)
            if (Objects.equals(user.getUsername(), name))
            {
                users.remove(user);
                return;
            }
        throw new NotFoundException();
    }

    @PutMapping("users/{name}")
    public void updateUser(@PathVariable("name") String name, @RequestBody UserProv userProv) throws Exception
    {
        if (userProv.getPassword() != userProv.getRepeatPassword())
            throw new BadRequestException();
        if (name.length() < 6) {
            throw new OtherErrorException();
        }

        if (!name.substring(0, 6).equals("update")) {
            throw new OtherErrorException();
        }

        for (int i = 0; i < name.length(); i++)
        {
            char c = name.charAt(i);
            if (!((c >= 'a' && c <= 'z') || c == '_' || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')))
                throw new BadRequestException();
        }

        for (User user : users)
            if (Objects.equals(user.getUsername(), name))
            {
                user.setPassword(userProv.getPassword());
                return;
            }
        throw new NotFoundException();
    }



    /*Map<String, ArrayList<Comment>> themes = new HashMap<String, ArrayList<Comment>>();


    @GetMapping("themes")
    public List<String> getTheme() {
        return new ArrayList<>(themes.keySet());
    }

    @PostMapping("themes")
    public void addTheme(@RequestBody String name) {
        themes.put(name, new ArrayList<Comment>());
    }

    @PutMapping("themes/{name}")
    public void updateUser(@PathVariable("name") String name, @RequestBody String new_name) {
        themes.put(new_name, new ArrayList<Comment>());
        themes.remove(name);
    }

    @DeleteMapping("themes/{name}")
    public void DelTheme(@PathVariable("name") String name)
    {
        themes.remove(name);
    }

    @GetMapping("themes/size")
    public Integer getThemeSize() {
        return themes.size();
    }

    @DeleteMapping("themes/all")
    public void DelAllTheme()
    {
        themes.clear();
    }

    @GetMapping("themes/{name}")
    public String getThemeToName(@PathVariable("name") String name) {
        StringBuilder ans = new StringBuilder();
        for (Comment comment : themes.get(name))
            ans.append(comment).append(" ");

        return ans.toString();
    }

    @PostMapping("themes/{name}/{user}")
    public void addComment(@PathVariable("name") String name, @PathVariable("user") String user, @RequestBody String text) {
        themes.get(name).add(new Comment(text, user));
    }

    @DeleteMapping("themes/{name}/{text}")
    public void DelCommentInTheme(@PathVariable("name") String name, @PathVariable("text") String text)
    {
        themes.get(name).removeIf(comment -> Objects.equals(comment.getText(), text));
    }

    @PutMapping("themes/{name}/{text}")
    public void updateComment(@PathVariable("name") String name, @PathVariable("text") String text, @RequestBody String new_text) {
        for (Comment comment : themes.get(name))
        {
            if (Objects.equals(comment.getText(), text))
            {
                comment.setText(new_text);
                break;
            }
        }
    }

    @GetMapping("themes/comments/{user}")
    public ArrayList<String> getCommentsToName(@PathVariable("user") String user) {
        ArrayList<String> ans = new ArrayList<String>();
        for (String name : themes.keySet())
        {
            for (Comment comment : themes.get(name))
            {
                if (Objects.equals(comment.getUser(), user))
                {
                    ans.add(comment.getText());
                }
            }
        }
        return ans;
    }

    @DeleteMapping("themes/comments/{user}")
    public void DelCommentsToName(@PathVariable("user") String user)
    {
        for (String name : themes.keySet())
            themes.get(name).removeIf(comment -> Objects.equals(comment.getUser(), user));
    }

    */


}
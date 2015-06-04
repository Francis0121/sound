package com.sound.server.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francis on 2015-05-03.
 */
@Controller
@ResponseBody
@RequestMapping(value="/auth")
public class AuthController {
    
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/isExist", method = RequestMethod.POST)
    public Map<String, Object> isExist(@RequestBody Map<String, Object> request){
        Map<String, Object> response = new HashMap<String, Object>();
        Auth auth = new Auth((String)request.get("id"));
        response.put("result", authService.selectIsExist(auth) != null);
        return response;
    }
    
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Map<String, Object> insert(@RequestBody Map<String, Object> request){
        
        Map<String, Object> response = new HashMap<String, Object>();
        Auth auth = new Auth((String)request.get("email"), (String)request.get("name"), (String)request.get("id"));
        if(authService.selectIsExist(auth) != null){
            response.put("result", false);
            return response;
        }
        
        authService.insert(auth);
        response.put("result", true);
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestBody Map<String, Object> request){
        Map<String, Object> response = new HashMap<String, Object>();
        Auth auth = new Auth((String)request.get("id"));
        authService.delete(auth);
        response.put("result", true);
        return response;
    }
    
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public Map<String, Object> select(@RequestBody Map<String,Object> request){
        Map<String, Object> response = new HashMap<String, Object>();
        if(authService.selectIsExist(new Auth((String)request.get("id"))) == null){
            response.put("result", false);
            return response;
        }
        response.put("auth", authService.selectAuth((String) request.get("id")));
        response.put("result", true);
        return response;
    }
}

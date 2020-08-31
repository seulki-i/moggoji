package dev.mvc.review.comment;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RcommentCont {
  
  @Autowired
  @Qualifier("dev.mvc.review.comment.RcommentProc")
  RcommentProcInter rcommnetProc = null;

  public RcommentCont(){
  }
  
  //´ñ±Û µî·Ï
  @ResponseBody
  @RequestMapping(value="/rcomment/create.do", method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
  public String create(RcommentVO rcommentVO){
    
    //System.out.println("´ñ±Û µî·Ï");
    
    JSONObject obj = new JSONObject();
    JSONArray msgs = new JSONArray();
    
    if(rcommnetProc.create(rcommentVO) == 1){
      msgs.put("success");
    }else{
      msgs.put("fail");
    }
   obj.put("msgs", msgs);
   
    return obj.toString();
  }

  //´ñ±Û ¸ñ·Ï
  @ResponseBody
  @RequestMapping(value="/rcomment/list.do", produces="application/json; charset=utf8", method=RequestMethod.GET)
  public ResponseEntity list(int reviewno) {
    //ResponseEntity : ÀÀ´ä + ÀÀ´ä ÄÚµå
    
    //System.out.println("´ñ±Û ¸ñ·Ï"); 
    
    
    HttpHeaders responseHeaders = new HttpHeaders();

    List<RcommentVO> list = rcommnetProc.list(reviewno);
    
    //int comment_count = rcommnetProc.comment_count(reviewno);
    //System.out.println("´ñ±Û °¹¼ö : "+comment_count);  
    
    JSONArray json = new JSONArray(list);

    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  
  //´ñ±Û »èÁ¦
  @ResponseBody
  @RequestMapping(value="/rcomment/deleteco.do", method=RequestMethod.POST, produces="application/json; charset=utf8")
  public String delete(int rcno, int memberno) {

    JSONObject obj = new JSONObject();
    JSONArray msgs = new JSONArray();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("memberno", memberno);
    hashMap.put("rcno", rcno);
     
    //System.out.println(hashMap.toString()); 
    
    int count = rcommnetProc.delete(hashMap); 
    //System.out.println("count : "+count);
    
    if(count == 1){
      msgs.put("success");
    }else{
      msgs.put("fail");
    }
   obj.put("msgs", msgs);
   
    return obj.toString();

  }
}

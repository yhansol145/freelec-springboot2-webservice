package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // 어느 컨트롤러든지 @LoginUser만 사용하면 세션정보를 가져올 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2Service 에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구현
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        
        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); 
        // > 이 코드를 계속해서 반복하게 만든다면 이후에 수정이나 개선이 필요할 때 모든 로직에 다시 선언해줘야한다. > @LoginUser 로 개선

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}

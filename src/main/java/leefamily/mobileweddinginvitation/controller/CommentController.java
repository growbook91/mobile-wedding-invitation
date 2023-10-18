package leefamily.mobileweddinginvitation.controller;

import leefamily.mobileweddinginvitation.domain.Comment;
import leefamily.mobileweddinginvitation.repository.CommentRepository;
import leefamily.mobileweddinginvitation.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CommentController {
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    private final CommentService commentService;
    // get mapping에서 열어주기
    // 읽어들이는 것
    @GetMapping(value = "/")
    public String list(Model model){
        List<Comment> comments = commentService.getCommentList();
        // FIXME : 이 모델은 뭐하는 것인가?
        model.addAttribute("comments", comments);
        return "index";
    }
    @GetMapping(value = "/comment")
    public String createForm() {
        return "createCommentForm";
    }

    // 댓글 생성하는 것
    @PostMapping(value = "/comment/new")
    public String create(CommentForm form) {
        // FIXME : 얘도...흠...어떻게 parameter를 넣어주는 거지..?
        Comment comment = new Comment(form.getName(), form.getContent(), form.getPassword());
        comment.setName(form.getName());
        commentService.writeComment(comment);
        return "redirect:/";
    }
    // TODO: 삭제하는 것

}
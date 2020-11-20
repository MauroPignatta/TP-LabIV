package edu.labIV.manager;

import edu.labIV.entity.Post;
import edu.labIV.exception.PostException;
import edu.labIV.logger.Logger;
import edu.labIV.mapper.PostMapper;
import edu.labIV.validator.PostValidator;

import java.util.List;

public class PostManager {

    private final PostValidator postValidator;
    private final PostMapper postMapper;
    private final Logger logger;

    public PostManager(PostValidator postValidator, PostMapper postMapper, Logger logger) {
        this.postMapper = postMapper;
        this.logger = logger;
        this.postValidator = postValidator;
    }

    public boolean savePost(Post post){
        boolean isSaved = false;
        try{
            postValidator.validatePost(post);
            postMapper.save(post);
            isSaved = true;
        }catch (PostException e){
            logger.logError(e.getError());
        }
        return isSaved;
    }

    public boolean deletePost(int userId, int postId){
        return postMapper.delete(userId, postId);
    }

    public Post getPost(int userId, int postId){
        return  postMapper.get(userId, postId);
    }

    public List<Post> getAllPost(int userId){
        return postMapper.getAll(userId);
    }

    public boolean updatePost(Post post){return postMapper.update(post);}
}

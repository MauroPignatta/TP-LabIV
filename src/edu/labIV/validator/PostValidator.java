package edu.labIV.validator;

import edu.labIV.entity.Post;
import edu.labIV.exception.*;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class PostValidator {

    public void validatePost(Post post) throws PostException {
        if (post == null)
            throw new NullPostException();
        validateText(post.getText());
        validateUrl(post.getUrl());
    }

    private void validateUrl(String urls) throws PostException {
        if (urls != null) {
            try {
                URL url = new URL(urls);
                InputStream i = null;
                try {
                    i = url.openStream();
                } catch (Exception ex) {
                    throw new NoResponseUrlException(urls);
                }
            } catch (MalformedURLException e) {
                throw new InvalidUrlException(urls);
            }
        }
    }

    private void validateText(String text) throws PostException {
        if (text == null)
            throw new NullTextException();
        if(text.isEmpty() || text.trim().isEmpty())
            throw new EmptyTextException();
        if(text.length() > Post.MAX_TEXT_LENGHT)
            throw new LongTextException(text.length());
    }
}

package org.acme.filter;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RestClientPathInfo {
    public record PathInfo(String path, String pathTemplate){};

    private PathInfo pathInfo;

    public PathInfo of(){
        return pathInfo;
    }

    public void setPathInfo(String path, String pathTemplate){
        this.pathInfo = new PathInfo(path, pathTemplate);
    }
}

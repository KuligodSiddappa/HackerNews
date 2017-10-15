package com.example.hackernews.newsmodel;


public class NewsDataModel {
    private String comment_text;

    private String story_text;

    private String[] _tags;

    private String url;

    private _highlightResult _highlightResult;

    private String story_title;

    private String author;

    private String num_comments;

    private String story_id;

    private String title;

    private String objectID;

    private String story_url;

    private String created_at;

    private String points;

    private String created_at_i;

    private String parent_id;

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getStory_text() {
        return story_text;
    }

    public void setStory_text(String story_text) {
        this.story_text = story_text;
    }

    public String[] get_tags() {
        return _tags;
    }

    public void set_tags(String[] _tags) {
        this._tags = _tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public _highlightResult get_highlightResult() {
        return _highlightResult;
    }

    public void set_highlightResult(_highlightResult _highlightResult) {
        this._highlightResult = _highlightResult;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(String num_comments) {
        this.num_comments = num_comments;
    }

    public String getStory_id() {
        return story_id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getStory_url() {
        return story_url;
    }

    public void setStory_url(String story_url) {
        this.story_url = story_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getCreated_at_i() {
        return created_at_i;
    }

    public void setCreated_at_i(String created_at_i) {
        this.created_at_i = created_at_i;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public class _highlightResult {
        private Author author;

        private Title title;

        private Story_text story_text;

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Story_text getStory_text() {
            return story_text;
        }

        public void setStory_text(Story_text story_text) {
            this.story_text = story_text;
        }
    }

    public class Story_text {
        private String[] matchedWords;

        private String matchLevel;

        private String value;

        private String fullyHighlighted;

        public String[] getMatchedWords() {
            return matchedWords;
        }

        public void setMatchedWords(String[] matchedWords) {
            this.matchedWords = matchedWords;
        }

        public String getMatchLevel() {
            return matchLevel;
        }

        public void setMatchLevel(String matchLevel) {
            this.matchLevel = matchLevel;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getFullyHighlighted() {
            return fullyHighlighted;
        }

        public void setFullyHighlighted(String fullyHighlighted) {
            this.fullyHighlighted = fullyHighlighted;
        }
    }

    public class Author {
        private String[] matchedWords;

        private String matchLevel;

        private String value;

        public String[] getMatchedWords() {
            return matchedWords;
        }

        public void setMatchedWords(String[] matchedWords) {
            this.matchedWords = matchedWords;
        }

        public String getMatchLevel() {
            return matchLevel;
        }

        public void setMatchLevel(String matchLevel) {
            this.matchLevel = matchLevel;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class Title {
        private String[] matchedWords;

        private String matchLevel;

        private String value;

        private String fullyHighlighted;

        public String[] getMatchedWords() {
            return matchedWords;
        }

        public void setMatchedWords(String[] matchedWords) {
            this.matchedWords = matchedWords;
        }

        public String getMatchLevel() {
            return matchLevel;
        }

        public void setMatchLevel(String matchLevel) {
            this.matchLevel = matchLevel;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getFullyHighlighted() {
            return fullyHighlighted;
        }

        public void setFullyHighlighted(String fullyHighlighted) {
            this.fullyHighlighted = fullyHighlighted;
        }
    }
}

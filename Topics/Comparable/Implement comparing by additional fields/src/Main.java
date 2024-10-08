class Article implements Comparable<Article> {
    private final String title;
    private final int size;

    public Article(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public int compareTo(Article otherArticle) {
        return Integer.valueOf(getSize()).equals(otherArticle.getSize()) ?
                getTitle().compareTo(otherArticle.getTitle()) :
                Integer.valueOf(getSize()).compareTo(otherArticle.getSize());
    }
}
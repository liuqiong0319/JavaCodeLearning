package learning.work.first.head.test.page562;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class Song implements Comparable<Song>{
    private String name;
    private String singer;
    private String style;
    private String time;

    public Song(String name, String singer, String style, String time) {
        this.name = name;
        this.singer = singer;
        this.style = style;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", style='" + style + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public int compareTo(Song o) {
        return name.compareTo(o.getName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;

        Song song = (Song) o;

        return name != null ? name.equals(song.name) : song.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

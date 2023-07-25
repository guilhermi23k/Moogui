package app.moogui.constants;
public enum Genres {

  // Define the IDs and names for each genre.
  ACTION(28, "Action"),
  ADVENTURE(12, "Adventure"),
  ANIMATION(16, "Animation"),
  COMEDY(35, "Comedy"),
  CRIME(80, "Crime"),
  DOCUMENTARY(99, "Documentary"),
  DRAMA(18, "Drama"),
  FAMILY(10751, "Family"),
  FANTASY(14, "Fantasy"),
  HISTORY(36, "History"),
  HORROR(27, "Horror"),
  MUSIC(10402, "Music"),
  MYSTERY(9648, "Mystery"),
  ROMANCE(10749, "Romance"),
  SCIENCE_FICTION(878, "Science Fiction"),
  TV_MOVIE(10770, "TV Movie"),
  THRILLER(53, "Thriller"),
  WAR(10752, "War"),
  WESTERN(37, "Western"),
  ACTION_AND_ADVENTURE(10759, "Action & Adventure"),
  KIDS(10762, "Kids"),
  SCI_FI_AND_FANTASY(10765, "Sci-Fi & Fantasy"),
  WAR_AND_POLITICS(10768, "War & Politics"),
  Western(37, "Western");

  // The ID for the genre.
  private final int id;

  // The name of the genre.
  private final String name;

  // Constructor for the `Genre` enum.
  Genres(int id, String name) {
    this.id = id;
    this.name = name;
  }

  // Get the ID for the genre.
  public int getId() {
    return id;
  }

  // Get the name for the genre.
  public String getName() {
    return name;
  }

  // Get the genre by its ID.
  public static String getGenreById(int id) {
	  for (Genres genre : Genres.values()) {
	      if (genre.getId() == id) {
	        return genre.getName();
	      }
	    }
	  return null;
  }
}


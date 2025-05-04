package com.beginner.endsection;

import static spark.Spark.*;

public class IMDBWebApp {
    public static void main(String[] args) {
        String[] movieTitles = {
                "The Last Samurai", "Minority Report", "Top Gun",
                "Rain Man", "Cocktail", "The Mummy", "American Made"
        };

        float[] movieRatings = {
                7.7F, 7.6F, 6.9F,
                8.0F, 5.9F, 5.4F, 7.1F
        };

        get("/", (req, res) -> {
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html><html><head><title>IMDB Ratings</title>");

            // Bootstrap CDN + styling
            html.append("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'/>");
            html.append("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js'></script>");
            html.append("<style>");
            html.append("body { background: #f0f8ff; padding: 30px; font-family: Arial; }");
            html.append(".movie-card { background: #fff; padding: 15px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-bottom: 15px; }");
            html.append("</style></head><body>");

            html.append("<div class='container'><h1 class='mb-4'>🎬 IMDB Ratings for Tom Cruise</h1>");
            html.append("<input type='text' class='form-control mb-3' id='searchBox' placeholder='Filter movies by title...' onkeyup='filterMovies()' />");

            html.append("<div id='movieList'>");
            for (int i = 0; i < movieTitles.length; i++) {
                html.append("<div class='movie-card' data-title='").append(movieTitles[i].toLowerCase()).append("'>")
                    .append("<h5>").append(movieTitles[i]).append("</h5>")
                    .append("<p>Rating: <strong>").append(movieRatings[i]).append("</strong> - ")
                    .append(getRating(movieRatings[i])).append("</p>")
                    .append("</div>");
            }
            html.append("</div></div>");

            // JavaScript for live filtering
            html.append("<script>");
            html.append("function filterMovies() {")
                .append("let input = document.getElementById('searchBox').value.toLowerCase();")
                .append("let movies = document.querySelectorAll('.movie-card');")
                .append("movies.forEach(m => {")
                .append("  let title = m.getAttribute('data-title');")
                .append("  m.style.display = title.includes(input) ? 'block' : 'none';")
                .append("});")
                .append("}");
            html.append("</script>");

            html.append("</body></html>");
            return html.toString();
        });
    }

    static String getRating(float rating) {
        if (rating <= 5.0){
            return "😞 Bad";
        } else if (rating <= 6.5){
            return "😐 Average";
        } else if (rating <= 7.0){
            return "🙂 Good";
        } else if (rating <= 8.0){
            return "😃 Very Good";
        } else {
            return "🤩 Amazing";
        }
    }
}

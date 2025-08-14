public class MoodSuggestions {
    public static String getSuggestion(String mood) {
        switch (mood.toLowerCase()) {
            case "happy":
                return "Keep up the positive vibes! Maybe share your joy with a friend.";
            case "neutral":
                return "Try a small creative activity to brighten your day.";
            case "stressed":
                return "Take a 5-minute breathing break or go for a short walk.";
            case "sad":
                return "Listen to uplifting music or talk to someone you trust.";
            default:
                return "Stay consistent with your habits and take care of yourself.";
        }
    }
}

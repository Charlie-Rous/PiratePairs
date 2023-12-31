public class Names {
    private static String[] names = { "Roderick",
            "Marissa",
            "Haleigh",
            "Kendal",
            "Faith",
            "Corrina",
            "Ervin",
            "Meadow",
            "Caleb",
            "Jordyn",
            "Kenia",
            "Leslie",
            "Akira",
            "Calli",
            "Laisha",
            "Bishop",
            "Walter",
            "Gilbert",
            "Dylan ",
            "Rosa",
            "Mason",
            "Ricky",
            "Ashly",
            "Joslyn",
            "Tiana",
            "Caleigh",
            "Jessenia",
            "Zoe",
            "Jonah",
            "Shea ",
            "Vicky",
            "Amelia",
            "Cornelius",
            "Eve",
            "Charisma",
            "Mari",
            "Chantal",
            "Annie",
            "Maya",
            "Cody",
            "Caleb",
            "Adonis",
            "Nikolas",
            "Jevon",
            "Harmony",
            "Cullen",
            "Josh",
            "Mackenzie",
            "Nicklaus",
            "Kaili",
            "Triston",
            "Carlie ",
            "Dixon",
            "Ellie",
            "Alexandre",
            "Nolan",
            "Patience",
            "Walker",
            "Hanna",
            "Ginger",
            "Devin",
            "Acacia",
            "Erik",
            "Jaret",
            "Nya",
            "Davis",
            "Jerod",
            "Lucas",
            "Giancarlo",
            "Paulina ",
            "Jaret ",
            "Carl",
            "Eryn",
            "Anaya",
            "Jocelyn",
            "Chris",
            "Angela",
            "Johnna",
            "Isaac",
            "Darnell",
            "Colby",
            "Zackary",
            "Kassidy",
            "Kendall",
            "Jessalyn",
            "Anders",
            "Paul",
            "Nicole",
            "Ella",
            "Carleigh",
            "Ayana",
            "Hasan",
            "Roy",
            "Betty",
            "Nikita",
            "Rhonda",
            "Rosa",
            "Quinn",
            "Morris",
            "Bowen",
            "Korbin",
            "Jaidyn",
            "Hayley",
            "Keagan",
            "Ramon" };
            static String[] resetNames = names;

    public static String randomName() {
        int randomIndex = (int) (Math.random() * names.length);
        String name = names[randomIndex];
        String[] tempNames = new String[names.length - 1];
        int index = 0;
        for (int i = 0; i < names.length; i++) {
            if (names[i] != name) {
                tempNames[index] = names[i];
                index++;
            }
        }
        names = tempNames;
        return name;
    }
    public static void resetNames() {
        names = resetNames;
    }
}

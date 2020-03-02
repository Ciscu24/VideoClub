
import io.VideoClub.Model.Item;
import io.VideoClub.Model.Repositories.RepositoryItems;
import io.VideoClub.View.GUI;

public class App {

    public static void main(String[] args) {
        //GUI.principal();
        RepositoryItems i = new RepositoryItems();

        i.addItem(new Item("Iron Man", "Está guapísima", 5.25) {
        });
        i.addItem(new Item("Joker", "Hermano, una locura", 10.5) {
        });
        i.addItem(new Item("Avengers: Infinity War", "Simplemente espectacular", 15) {
        });
        i.addItem(new Item("Avengers: Endgame", "Putamente espectacular hermano, espectacular", 15) {
        });
        i.addItem(new Item("La Momia", "Un clásico hermano, un clásico", 3.25) {
        });
        
        

        i.writeItem();
        
        /*i.deleteItem("joker");
        i.deleteItem("iron man");
        
        i.writeItem();*/

    }
}

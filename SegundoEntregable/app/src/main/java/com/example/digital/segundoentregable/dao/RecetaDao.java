package com.example.digital.segundoentregable.dao;

import com.example.digital.segundoentregable.R;
import com.example.digital.segundoentregable.model.Receta;

import java.util.ArrayList;
import java.util.List;

public class RecetaDao {

    private List<Receta> listaDeRecetas;

    public RecetaDao(List<Receta> listaDeRecetas) {
        this.listaDeRecetas = new ArrayList<>();
    }

    public RecetaDao() {
    }

    public List<Receta> cargarReceta() {
        this.listaDeRecetas = new ArrayList<>();
        listaDeRecetas.add(new Receta("Tamarindo", "2 tazas de pulpa de tamarindo\n" +
                "1 taza de azúcar\n" +
                "10 tazas de agua\n" +
                "Hielo", "    Para obtener la pulpa deberás dejar remojando los tamarindos durante la noche en abundante agua. En la mañana siguiente retira la cáscara y demenuza para quitar las semillas. Otra alternativa es comprar la pulpa pre empacada en el supermercado.\n" +
                "Coloca la pulpa del tamarindo y las 10 tazas de agua en el vaso de la batidora y bate a velocidad mediana por unos 5 minutos. Esto ayudará a que la pulpa se licúe y mezcle con el agua parejamente.\n" +
                "Filtra el contenido, si es necesario, y devuélve el líquido a la batidora.\n" +
                "Añade el azúcar y vuelve a batir por 2-3 minutos.\n" +
                "El jugo de tamarindo está listo. Sirve inmediatamente con hielo.", R.drawable.tamarindo));


        listaDeRecetas.add(new Receta("Limón",
                "2 limones, cortadas en cuartos y sin semillas\n" +
                        "    8 tazas de agua fría\n" +
                        "    ½ taza a 1 taza de azuzar, ajuste de acuerdo a su gusto\n" +
                        "    Cubitos de hielo al gusto\n", "\n" +
                "    Coloque los pedazos de limón en la licuadora con 4 tazas de agua y el azúcar.\n" +
                "    Licue los ingredientes hasta obtener una mezcla homogénea, quedara un poco espumoso y con un color medio blanco.\n" +
                "    Cuele o cierna la limonada y mezclar con el resto de agua.\n" +
                "    Sirva inmediatamente acompañado de hielo.\n", R.drawable.limonada));


        listaDeRecetas.add(new Receta("Jamaica",
                "1 taza flor de Jamaica\n" +
                        "    12 tazas de agua\n" +
                        "    ½ taza azúcar o más al gusto, puedes usar stevia al gusto\n" +
                        "    Hielos, para servir\n", "\n" +
                "    Coloca 4 tazas de agua en una ollita junto la flor de jamaica.\n" +
                "    Deja que hierva unos 5 minutos a fuego medio bajo.\n" +
                "    Cuela el agua y reserva las flores.\n" +
                "    Agrega el agua restante, el azúcar y mezcla bien.\n" +
                "    Puedes endulzar a tu gusto si crees que le falta azúcar o si te gusta muy dulce el agua.\n" +
                "    Refrigera para servir bien fría o sirve con hielos.\n" +
                "    Si se te hace muy fuerte el sabor agrega más agua al gusto hasta que esté con el sabor que más te gusta.\n", R.drawable.jamaica));


        return listaDeRecetas;
    }

}

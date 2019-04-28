package com.example.ma.roombase.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ma.roombase.R;
import com.example.ma.roombase.dao.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 16/01/18.
 */

public class PersonRecyclerAdapter extends RecyclerView.Adapter implements ItemTouchHelperAdapter {
    private List<Person> persons;
    private PersonClickeable personClickeable;

    public PersonRecyclerAdapter(PersonClickeable clickeable) {
        persons = new ArrayList<>();
        personClickeable = clickeable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.person_cell_view, parent,false);
        final PersonViewHolder viewHolder = new PersonViewHolder(view);
        ImageView deleteButton = view.findViewById(R.id.delete_person_id);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personClickeable.deletePerson(persons.get(viewHolder.getAdapterPosition()));
            }
        });
        ImageView editPerson = view.findViewById(R.id.edit_person_id);
        editPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personClickeable.editPerson(persons.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Person person = persons.get(position);
        PersonViewHolder personViewHolder = (PersonViewHolder) holder;
        personViewHolder.loadData(person);
    }

    @Override
    public int getItemCount() {
        return (persons == null) ? 0 : persons.size();
    }


    public void addPersons(List<Person> personList) {
        this.persons.addAll(personList);
        notifyDataSetChanged();
    }

    public void deletePerson(Person person) {
        persons.remove(person);
        notifyDataSetChanged();
    }

    public void addPerson(Person person) {
        persons.add(person);
        notifyDataSetChanged();
    }

    @Override
    public void onMove(int posicionAnterior, int posicionPosterior) {

    }

    @Override
    public void onSwipe(int posicion) {
        personClickeable.deletePerson(persons.get(posicion));
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView lastname;
        private TextView favFood;
        private ImageView deletePerson;
        private ImageView editPerson;

        public PersonViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.person_name_id);
            lastname = itemView.findViewById(R.id.person_lastname_id);
            favFood = itemView.findViewById(R.id.person_favfood_id);
            deletePerson = itemView.findViewById(R.id.delete_person_id);
            editPerson = itemView.findViewById(R.id.edit_person_id);
        }

        public void loadData(Person person) {
            name.setText(person.getName());
            lastname.setText(person.getLastname());
            favFood.setText(person.getFavouriteFood());
        }
    }

    public interface PersonClickeable {
        public void editPerson(Person person);
        public void deletePerson(Person person);
    }
}

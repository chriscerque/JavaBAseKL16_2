package net.ent.etrs.repaspatient.start;

import net.ent.etrs.repaspatient.presenter.Presenter;

public class Launcher {

    private Launcher() {
    }

    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        presenter.excecuter();
    }
}

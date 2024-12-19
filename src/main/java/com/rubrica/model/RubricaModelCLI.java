// Versione precedente di RubricaModel :
//     - gestisce dati solo a run-time ( no salvataggio in locale )
//     - CLI su terminale ( versione precedente di Java Swing )

package com.rubrica.model;
import java.util.Scanner;
import java.util.Vector;

public class RubricaModelCLI {

    private Vector<Persona> contatti;

    public RubricaModelCLI() {
        contatti = new Vector<>();
    }

    public Vector<Persona> getContatti() {
        return contatti;
    }

    public void aggiungiContatto(Persona persona) {
        contatti.add(persona);
        System.out.println("Contatto aggiunto con successo.");
    }

    public void visualizzaContatti() {
        if (contatti.isEmpty()) {
            System.out.println("La rubrica è vuota.");
        } else {
            for (int i = 0; i < contatti.size(); i++) {
                System.out.println((i + 1) + ". " + contatti.get(i));
            }
        }
    }

    public void modificaContatto(int indice, String nome, String cognome, String indirizzo, String telefono, Integer eta) {
        if (indice >= 0 && indice < contatti.size()) {
            Persona persona = contatti.get(indice);
            persona.setNome(nome);
            persona.setCognome(cognome);
            persona.setIndirizzo(indirizzo);
            persona.setTelefono(telefono);
            persona.SetEta(eta);
            System.out.println("Contatto modificato con successo.");
        } else {
            System.out.println("Indice non valido.");
        }
    }

    public void eliminaContatto(int indice) {
        if (indice >= 0 && indice < contatti.size()) {
            contatti.remove(indice);
            System.out.println("Contatto eliminato con successo.");
        } else {
            System.out.println("Indice non valido.");
        }
    }

    public static void main(String[] args) {
        RubricaModelCLI rubrica = new RubricaModelCLI();
        Scanner scanner = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("\n--- Rubrica Telefonica ---");
            System.out.println("1. Aggiungi contatto");
            System.out.println("2. Visualizza contatti");
            System.out.println("3. Modifica contatto");
            System.out.println("4. Elimina contatto");
            System.out.println("5. Esci");
            System.out.print("Scegli un'opzione: ");

            scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Inserisci cognome: ");
                    String cognome = scanner.nextLine();
                    System.out.print("Inserisci indirizzo: ");
                    String indirizzo = scanner.nextLine();
                    System.out.print("Inserisci numero di telefono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Inserisci età: ");
                    Integer eta = Integer.parseInt(scanner.nextLine());
                    rubrica.aggiungiContatto(new Persona(0, nome, cognome, indirizzo, telefono, eta));
                    break;

                case 2:
                    rubrica.visualizzaContatti();
                    break;

                case 3:
                    rubrica.visualizzaContatti();
                    System.out.print("Inserisci l'indice del contatto da modificare: ");
                    int indiceModifica = scanner.nextInt() - 1;
                    scanner.nextLine();
                    System.out.print("Inserisci il nuovo nome: ");
                    String nuovoNome = scanner.nextLine();
                    System.out.print("Inserisci il nuovo cognome: ");
                    String nuovoCognome = scanner.nextLine();
                    System.out.print("Inserisci il nuovo indirizzo: ");
                    String nuovoIndirizzo = scanner.nextLine();
                    System.out.print("Inserisci il nuovo numero di telefono: ");
                    String nuovoNumero = scanner.nextLine();
                    System.out.print("Inserisci la nuova età: ");
                    Integer nuovaEta = Integer.parseInt(scanner.nextLine());
                    rubrica.modificaContatto(indiceModifica, nuovoNome, nuovoCognome, nuovoIndirizzo, nuovoNumero, nuovaEta);
                    break;

                case 4:
                    rubrica.visualizzaContatti();
                    System.out.print("Inserisci l'indice del contatto da eliminare: ");
                    int indiceElimina = scanner.nextInt() - 1;
                    rubrica.eliminaContatto(indiceElimina);
                    break;

                case 5:
                    System.out.println("Uscita dal programma.");
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        } while (scelta != 5);

        scanner.close();
    }
}
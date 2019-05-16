package projpa.Iu.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import projpa.GameLogic.GameLogic;
import projpa.GameLogic.StateMachine.*;
import projpa.GameLogic.Traps.Trap;
import projpa.GameLogic.User.User;

/**
 * Class to see the game logic happening
 * @author Vitor
 * @author João
 */

public class TextInterface {

    private boolean run = false;
    GameLogic game;

    /**
     * This constructor starts with the game constructed
     * @param game
     */
    public TextInterface(GameLogic game){

        this.game = game;
        
    }

    /**
     * This constructor creates a new game
     */
    public TextInterface(){

        this.game = new GameLogic();

    }

    /**
     * This function is the run of the game
     */
    public void run(){

        // This function is the game that the user can see
        
        while(!run){
            
            int option = MainMenu();
            
            switch(option){
                
                case 4:
                    
                    mostrapont();
                    
                    break;
                case 3:
                
                    extraSettings();
                    
                    break;
                case 2:

                    loadgame();
                    
                    break;
                case 1:
                    
                    //go to game
                    startGame();
                    
                    break;
            
                case 0:
                    
                    run = true;
                    
                    break;
            
            }
            
        }
        
    }
    
    public void loadgame(){
        
        System.out.println("Tem a certeza que pertendo ler o savegame");
        
        String option = " ";
      
                option = scanString("Deseja mesmo carregar o jogo (Y or N)?");

                        
            
            if (option.toLowerCase().equals("y")){
        	              
                try {

                        FileInputStream fi = new FileInputStream(new File("savegame.txt"));
                        ObjectInputStream oi = new ObjectInputStream(fi);

                        // Read objects
                        GameLogic pr1 = (GameLogic) oi.readObject();	

                        oi.close();
                        
                        this.game = new GameLogic(pr1);
                        
                } catch (IOException e) {

                        e.printStackTrace();

                } catch (ClassNotFoundException ex) {
                Logger.getLogger(TextInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                

            startGame();

        }        

    }

    /**
     * This function show if a string is a file or not
     * @param filename
     * @return true if exists
     */
    public boolean validateTextFile(String filename){
        return false;
    }

    /**
     * This function returns the option of the Main Menu
     * @return intger option
     */
    public int MainMenu() {

        int option = -1;

        while (option < 0 || option > 4) {

            clearConsole();
            System.out.println(MenuConstants.mainMenuConst);
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }

    /**
     * This function is a simple menu for the beginning state
     */
    public void AwaitBeginningMenu(){
        
        clearConsole();
        System.out.println(MenuConstants.beginningConst);

    }

    /**
     * This fucntion is the UI for the AwaitBeginning state
     */
    public void ui_AwaitBeginning(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if(statesOfTheGame instanceof AwaitBeginning){
            
            AwaitBeginningMenu();

            String name = new String();
            name = scanString("Insira o nome do Jogador: ");
            
            User user = new User(name);

            this.game.setUser(user);
            this.game.Inputbegining();

        }            

    }

    /**
     * This function returns the option of the Crew Member Selection Menu
     * @return intger option
     */
    public int CrewMemberSelectionMenu() {

        int option = -1;

        while (option < 0 || option > 13) {

            clearConsole();
            System.out.println(MenuConstants.crewMemberSelectionConst);
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }

    /**
     * This function converts the options number to a crew member
     * @param option
     * @return the option crew member
     */
    public String getTypeOfCrewMember(int option){

        String crewMember = null;

        switch (option) {
            
            case 1:
                crewMember = "Captain";
                break;
            
            case 2:
                crewMember = "Commander";
                break;
        
            case 3:
                crewMember = "CommsOfficer";
                break;

            case 4:
                crewMember = "Doctor";
                break;
            
            case 5:
                crewMember = "Engineer";
                break;
        
            case 6:
                crewMember = "MoralOfficer";
                break;

            case 7:
                crewMember = "NavigationOfficer";
                break;
            
            case 8:
                crewMember = "RedShirt";
                break;
        
            case 9:
                crewMember = "ScienceOfficer";
                break;

            case 10:
                crewMember = "SecurityOfficer";
                break;
            
            case 11:
                crewMember = "ShuttlePilot";
                break;
        
            case 12:
                crewMember = "TransporterChief";
                break;
        
            }

        return crewMember;

    }

    /**
     * This function is the UI for the AwaitCrewMemberSelection state
     */
    public void ui_AwaitCrewMemberSelection(){

       int option[] = new int[2];

       IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitCrewMembersSelection) {
            
            int i;
            
            for (i = 0; i < 2; i++) {
                
                option[i] = CrewMemberSelectionMenu();

                if (option[i] == 0) {
                    
                    if (i == 0) {
                        
                        this.game = new GameLogic();
                        break;

                    }                    
                    else
                        i--;

                }
                else
                    if(option[i] == 13){

                        i = 2;
                        this.game.selectrandomCrewMembers(); 

                    }
                    else{

                        if (i == 1)
                            if (option[0] == option[1])
                                i = 0;                            

                    }                

            }

            if (i != 3)
                this.game.selectCrewMembers(getTypeOfCrewMember(option[0]), getTypeOfCrewMember(option[1]));

        }

    }

    /**
     * This function returns the option of the Crew Member 1 Selection Menu
     * @return integer option
     */
    public int CrewMember1RoomSelectionMenu() {

        int option = -1;

        while (option < 0 || option > 12) {

            clearConsole();
            System.out.println(MenuConstants.CrewMember1RoomSelectionConst);
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }

    /**
     * This function returns the option of the Crew Member 2 Selection Menu
     * @return integer option
     */
    public int CrewMember2RoomSelectionMenu() {

        int option = -1;

        while (option < 0 || option > 12) {

            clearConsole();
            System.out.println(MenuConstants.CrewMember2RoomSelectionConst);
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }

    /**
     * This function is the UI for the AwaitThirdTokenFirstCrewMember state
     */
    public void ui_AwaitThirdTokenFirstCrewMember(){
        
        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitThirdTokenFirstCrewMember) {
            
            int room = CrewMember1RoomSelectionMenu();

            if(room != 0)
                this.game.placeFirstCrewMember(room);            
            else
                this.game.goBack();

        }

    }

    /**
     * This function is the UI for the AwaitThirdTokenSecondCrewMember state
     */
    public void ui_AwaitThirdTokenSecondCrewMember(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitThirdTokenSecondCrewMember) {
            
            int room = CrewMember2RoomSelectionMenu();

            if(room != 0)
                this.game.placeSecondCrewMember(room);            
            else
                this.game.goBack();

        }

    }

    public String chooseParticleDisperserMenu(){
        
        int option = -1;
        int i = 0;
        ArrayList<Trap> particleDisperser = this.game.getParticleDisperserTraps();

        while (option < 0 || option > i) {

            i = 0;
            clearConsole();
            System.out.println(MenuConstants.ChooseParticleDisperserConst);

            for (Trap trap : particleDisperser) {
                
                i++;
                System.out.println(i + " - Particle Disperser na room " + trap.getRoom().ReturnName());

            }

            System.out.println("0 - Voltar");

            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        if(option == 0)
            return "Sair";
        else{
            option = option - 1;
            return particleDisperser.get(option).getRoom().ReturnName();
        }
            
    }

    /**
     * This function is the UI for the AwaitChooseParticleDisperser state
     */
    public void ui_AwaitChooseParticleDisperser(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitChooseParticleDisperser) {
            
            String option = chooseParticleDisperserMenu();

            switch (option) {

                case "Sair":
                    
                    this.game.goBack();

                    break;
            
                default:

                    this.game.detonateParticleDisperser(option);

                    break;
                    
            }

        }

    }

    public void ui_AwaitDiceRolling(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if(statesOfTheGame instanceof AwaitDiceRolling){

            clearConsole();
            int crewMemberIndex = SelectCrewMemberMenu(); 

            if (crewMemberIndex != 0)
                this.game.atack(crewMemberIndex - 1);
            else
                this.game.goBack();

        }

    }

    public void ui_AwaitSaveGame(){
        
        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if(statesOfTheGame.wassaved() == true){
            // saved game start with the last state, so save game... this fix it
            statesOfTheGame.goBack();
        }
        
        if (statesOfTheGame instanceof AwaitSaveGame) {
            
            String option = " ";
              
              option = scanString("Deseja mesmo guardar (Y or N)?");

    
            
            if (option.toLowerCase().equals("y"))
                statesOfTheGame.saveGame();
                        
        }

    }

    public int MenuRestPhase(){
    
        int option = -1;

        while (option < 0 || option > 8) {
            
            clearConsole();
            System.out.println("Encontra-se na RestPhase. \nO que pretende fazer:\n");
            System.out.println("Inspiration Points (IP):" + game.getInspirationPoints().getInspstate());
            System.out.println("\n1- Adicionar 1 para a vida (custa 1 IP)");
            System.out.println("2- Reparar 1 Hull (custa 1 IP)");
            System.out.println("3- Construir 1 Organic Detonator (custa 2 IP)");
            System.out.println("4- Adicionar 1 ao movimento (custa 4 IP)");
            System.out.println("5- Construir 1 Particle Desperser (custa 5 IP)");
            System.out.println("6- Adicionar 1 Sealed Room Token (custa 5 IP)");
            System.out.println("7- Adicionar 1 Attack Die extra (custa 6 IP)");
            System.out.println("8- Adicionar 1 no resultado do dado (custa 6 IP)");
            System.out.println("0- Ir para o proximo turno\n");
            
            option = scanInteger("Insira uma opcao: ");

        }

        return option;
    
    }

    public void ui_AwaitRestPhaseActions(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitRestPhaseActions) {
            //interface
            
            int opcao = MenuRestPhase();
            
            if(opcao >=0 && opcao < 9){
                
                switch(opcao){
                    
                    case 1:
                        
                        this.game.addonehealth();
                        
                        break;

                    case 2:
                        this.game.repairhull();
                        
                        break;    

                    case 3:
                        
                        this.game.buildorganicdetonator();
                        
                        break;

                    case 4:
                        
                        opcao = SelectCrewMemberMenu();
                        
                        if (opcao != 0)
                            this.game.addonemovement(opcao - 1);
                        
                        break;

                    case 5:
                        
                        this.game.builoneparticleddispenser();
                        
                        break; 

                    case 6:
                        
                        this.game.gainonesealedromtoken();
                        
                        break;   

                    case 7:
                        
                        opcao = SelectCrewMemberMenu();
                        
                        if (opcao != 0)                        
                            this.game.gainoneattack(opcao);
                        
                        break; 

                    case 8:
                        
                        this.game.addonethedice();
                        
                        break; 

                    case 0:
                        this.game.nextTurn();
                        break;  

                }
                
            }
            
        }

    }

    /**
     * This function returns the option of the Crew Member 2 Selection Menu
     * @return integer option
     */
    public int CrewPhaseActionsMenu() {

        int option = -1;
      
        while (option < 0 || option > 10) {

            clearConsole();
            showStatus();
            System.out.println(MenuConstants.CrewPhaseActionsConst);
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }

    /**
     * This function returns the crew member to apply moves
     * @return <code>crew member index + 1</code> 
     */
    public int SelectCrewMemberMenu(){

        int option = -1;
        ArrayList<Integer> options = this.game.getCrewMembersForMenu();

        while(!options.contains(option)){

            clearConsole();
            System.out.println(MenuConstants.beginningConst + this.game.toStringAvailableCrewMembers(options));
            
            option = scanInteger("\nInsira a opcao que pretende: ");

        }
        
        return option;

    }
    
    public void showStatus(){
        
        System.out.println("-----Status-----\n");
        if (this.game.returnplayerslocation(0)!= "SecretRoom"){
        System.out.println("Crew Member 1 - Name: " 
            + this.game.getCrewMember1Name() 
            + " - Location: " 
            + this.game.returnplayerslocation(0)
            + " - Can move: "
            + this.game.getCrewMembers()[0].getMovementToDo()
        );}
        else 
            System.out.println("Crew Member 1 - Name: " + this.game.getCrewMember1Name() + " is dead");
        
        if (this.game.returnplayerslocation(1)!= "SecretRoom"){
        System.out.println("Crew Member 2 - Name: " 
            + this.game.getCrewMember2Name() 
            + " - Location: " 
            + this.game.returnplayerslocation(1)
            + " - Can move: "
            + this.game.getCrewMembers()[1].getMovementToDo()
        );}
        else
             System.out.println("Crew Member 2 - Name: " + this.game.getCrewMember2Name() + " is dead");
        
        
        System.out.println("\n-----Aliens-----\n");
        
        for (int i = 0; i < game.getAliens().size();i++){
            
            System.out.println("Alien number:" + (i + 1) 
            + " is in: " + game.getAliens().get(i).getRoom().ReturnName());
            
        }
        
        System.out.println("\nHealth: " + this.game.getHealthHealthTracker());
        System.out.println("Hull: " + this.game.getHullHealth());
        System.out.println("Action Points: " + this.game.getActionPoints());       
        
        System.out.println("\n-----Journey Location-----\n");
        
        for (int i = 0; i < 15;i++){
            
            System.out.print(this.game.getJourneyTracker().getJourneyTrackers()[i]);
                
            if (this.game.getJourneyTracker().getJourneyState() == i)
                System.out.print("  <- you are here");
                
            System.out.print("\n");
        }
        System.out.println("\n--------------------\n");
    }
    

    /**
     * This function does all the Crew Phase Actions
     */
    public void ui_AwaitCrewPhaseActions(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitCrewPhaseActions) {
            
            int option = CrewPhaseActionsMenu();
            
            switch(option){

                //If is move
                case 1:

                    this.game.move(0, 0);
                    ui_Move();

                    break;

                //If is atack
                case 2:

                    this.game.atack(0); 
                    ui_Atack();

                    break;

                //If Heal One Health
                case 3:

                    this.game.heal(); 

                    break;

                //If Fix One Hull
                case 4:

                        this.game.fixOneHull(); 

                    break;

                //If Setting trap
                case 5:

                    this.game.setTrap(0, 0);
                    ui_SetTrap();

                    break;

                //If detonate particle disperser
                case 6:

                    this.game.detonateParticleDisperser(null);

                    break;

                //If seal room
                case 7:

                    this.game.sealRoom(0);
                    ui_SealRoom();

                    break;

                //If save game
                case 8:

                    this.game.saveGame();

                    break;

                //If skip to next turn
                case 9:

                    this.game.nextTurn();

                    break;

                //RedShirt Special
                case 10:

                    this.game.redShirtSpecial();

                    break;

                //If is exit
                case 0:

                    this.game.goBack();

                    break;

            }

        }

    }

    public int SelectRoomToMove(int index){

        int option = -1;        
        ArrayList<Integer> options = this.game.getNearAvailableRooms(index);
        String MenuRooms = this.game.getRoomsNear(index);

        while (!options.contains(option)) {
            
            clearConsole();
            System.out.println(MenuRooms);
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }
    
    public int SelectRoomToMovespecial(int index){
        
        int option = -1,allcorrect = 1;
        clearConsole();
        System.out.println("Foi escolhido um Transporter Cheif, logo pode escolher qualquer local:");
        
        System.out.println("1- Bridge\n" + "2- Sick Bay\n" + "3- Brig\n" + "4- Crew Quarters\n" + 
                "5- Conference Room\n" + "6- Shuttle Bay\n" + "7- Weapons Bay\n" + "8- Mess Hall" +
                "9- Engineering\n" + "10- Astrometrics" + "11- Holodeck" + "12- Hydroponics");
        
        
        do{

            while (option <= 0 || option >12){
                option = scanInteger("\nInsira a opcao que pretende: ");
            }

            if (this.game.seelockedroom(option) == true){
                System.out.println("\nSala Selada");
            }
            else
                allcorrect=0;
                
        }while (allcorrect != 0);
        
        return option;
    }
            
    /**
     * This function in to do a move with the crew member
     */
    public void ui_Move(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitPeekRoom) {
            
            int index = SelectCrewMemberMenu();

            if(index != 0){
                
                if(this.game.isAnTransporterChief(index - 1)){
                    
                    int option = SelectRoomToMovespecial(index - 1);

                    if (option != 0) 
                        this.game.move(index - 1, option);
                    else
                        this.game.goBack();
                    
                }
                else{

                    int option = SelectRoomToMove(index - 1);
                    
                    if (option != 0) 
                        this.game.move(index - 1, option);
                    else
                        this.game.goBack();                    
                
                }

            }
            else{

                this.game.goBack();
            
            }
            
        }

    }

    public int SelectRoomToSeal(){

        int option = -1;
        ArrayList<Integer> options = this.game.roomsToSeal();

        while(!options.contains(option)){

            clearConsole();
            System.out.println(this.game.toStringRoomsToSeal());
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }

    /**
     * This function in to Seal a room
     */
    public void ui_SealRoom(){
        
        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitPeekRoom) {
            
            int option = SelectRoomToSeal();

            if (option != 0)
                this.game.sealRoom(option); 
            else
                this.game.goBack();

        }

    }

    public int SelectTrap(){

        int option = -1;
        ArrayList<Integer> options = this.game.getAvailableTraps();

        while(!options.contains(option)){

            clearConsole();
            System.out.println(this.game.toStringAvailableTraps());
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }    

    public int SelectTrapRoom(int trapIndex){

        int option = -1;
        ArrayList<Integer> options = this.game.getAvailableTrapRooms(trapIndex);

        while(!options.contains(option)){

            clearConsole();
            System.out.println(this.game.toStringAvailableTrapRooms(trapIndex));
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }
    
    /**
     * This function in to set a trap
     */
    public void ui_SetTrap(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitPeekRoom) {
            
            int trapIndex = SelectTrap();

            if (trapIndex != 0){
            
                int roomIndex = SelectTrapRoom(trapIndex);

                if (roomIndex != 0) {
                    
                    this.game.setTrap(roomIndex, trapIndex);

                }
                else
                    this.game.goBack();
            
            }                
            else
                this.game.goBack();

        }

    }

    /**
     * This function is to do a crew member atack
     */
    public void ui_Atack(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof AwaitPeekRoom) {
            
            int option = SelectCrewMemberMenu();

            if(option != 0){

                this.game.atack(option - 1); 

            }
            else{

                this.game.goBack(); 
            
            }

        }

    }

    public int MenuGameOver(){

        int option = -1;

        while (option < 1 || option > 2) {

            clearConsole();
            System.out.println(MenuConstants.GameOverConst);
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }

    /**
     * This function ends the game
     */
    public void ui_GameOver(){

        IStates statesOfTheGame = this.game.getStateOfTheGame();

        if (statesOfTheGame instanceof GameOver) {
            
            this.game.savetoscorefile();
            
            int option;
            System.out.println( "==== Game Over =====\n");
            

            // Player score
            System.out.println("---Pontuação---");
            
            HashMap<String, Integer> scoreloader = new HashMap<String, Integer>();
            scoreloader = this.game.scoreopener();
            
            for (String i : scoreloader.keySet()) {
              System.out.print("Nome: " + i + " com Pontuação: " + scoreloader.get(i));
              if(i == this.game.getUserName() && scoreloader.get(i)== this.game.getUser().getPoints())
                  System.out.print(" <- Aqui estas tu");
              System.out.print("\n");
            }
                    
            option = MenuGameOver();       
            this.game = new GameLogic();
            
            if (option == 1){

                startGame();
            
            }                

        }

    }

    /**
     * This function clears the console
     */
    public void clearConsole() {

        if (System.getProperties().getProperty("os.name").contains("Windows")) {

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                //e.printStackTrace();
            }

        }
        else{

            for (int i = 0; i < 50; i++) {
                System.out.println("\n");
            }

        }        

    }

    /**
     * This function returns an integer from the keyboard
     * @param message
     * @return the integer read from the keyboard
     */
    public int scanInteger(String message) {

        Scanner sc = new Scanner(System.in);

        System.out.println(message);

        while (!sc.hasNextInt()) sc.next();

        return sc.nextInt();

    }

    /**
     * This function reads a string from the keyboard
     * @param message
     * @return the read string
     */
    public String scanString(String message) {

        Scanner sc = new Scanner(System.in);

        System.out.println(message);

        return new String(sc.nextLine());

    }

    /**
     * This function charges the game
     * @param filename
     * @return true if the game was successfully charged 
     */
    public boolean chargeGame(String filename) {

        // This function is used to charge all data to continue the previous game
        return true;

    }

    /**
     * This function is to save the game
     * @param fileName
     * @return true if the game has been successefully saved
     */
    public boolean saveGame(String fileName) {

        //This function is used to save the game in a text File
        return true;

    }
    
    /**
     * This function is the game practice
     */
    public void startGame(){
        
        //This function is the interaction with the game core and the user
        IStates statesOfTheGame = this.game.getStateOfTheGame();

        //while not gameOver
        while(!(statesOfTheGame instanceof GameOver)){

            statesOfTheGame = this.game.getStateOfTheGame();

            if (statesOfTheGame instanceof AwaitBeginning) 
                ui_AwaitBeginning();

            if(statesOfTheGame instanceof AwaitCrewMembersSelection)
                ui_AwaitCrewMemberSelection();

            if (statesOfTheGame instanceof AwaitThirdTokenFirstCrewMember)
                ui_AwaitThirdTokenFirstCrewMember();

            if (statesOfTheGame instanceof AwaitThirdTokenSecondCrewMember)
                ui_AwaitThirdTokenSecondCrewMember();

            if (statesOfTheGame instanceof AwaitChooseParticleDisperser)
                ui_AwaitChooseParticleDisperser();

            if (statesOfTheGame instanceof AwaitDiceRolling)
                ui_AwaitDiceRolling();

            if (statesOfTheGame instanceof AwaitSaveGame)
                ui_AwaitSaveGame();

            if (statesOfTheGame instanceof AwaitRestPhaseActions) 
                ui_AwaitRestPhaseActions();

            if(statesOfTheGame instanceof AwaitCrewPhaseActions)
                ui_AwaitCrewPhaseActions();

            if (statesOfTheGame instanceof GameOver)
                ui_GameOver();

        }
        
    }

    private void extraSettings() {

        System.out.println("1- modifica o JourneyTracker");
        System.out.println("2- modifica o HealthTracker");
        System.out.println("3- modifica o Hulltracker");
        System.out.println("Outro numero para sair");

        int choice = scanInteger("Que opcao pretende: ");
        
        if (choice == 1){
            
            int pos;

            do{
                pos = scanInteger("Que posição pretende mudar?"); 
            }while(pos <= 0 || pos > 13);
            
            String info;

            do {
                info = scanString("Que String prende meter nessa posição?");
            } while (this.game.validateJourneyTrackerOption(info));                    

            Boolean verify = this.game.getJourneyTracker().changeJourney(pos, info.toUpperCase());
        
            if (verify == false)
                System.out.println("Não foi possivel modificar o Journey Tracker");
        
        }
        
        if (choice == 2){
            
            int pos = scanInteger("Qual o valor que pretende introduzir?");            
            this.game.setHealthTrackervalue(pos);
        
        }
        
        if (choice == 3){
            
            int pos = scanInteger("Qual o valor que pretende introduzir?");            
            this.game.setHullTrackervalue(pos);
        
        }
        
    }

    private void mostrapont() {

            System.out.println("---Pontuação---");
            
            HashMap<String, Integer> scoreloader = new HashMap<String, Integer>();
            scoreloader = this.game.scoreopener();
            
            for (String i : scoreloader.keySet()) {
              System.out.print("Nome: " + i + " com Pontuação: " + scoreloader.get(i));
              System.out.print("\n");
            }

            String opt = scanString("\nPressione Enter para continuar...");

    }
    
}

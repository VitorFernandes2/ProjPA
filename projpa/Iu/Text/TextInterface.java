package projpa.Iu.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    private GameLogic game;

    /**
     * This constructor starts with the game constructed
     * @param game
     */
    public TextInterface(GameLogic game){

        this.game = game;
        
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

            if (this.game.LoadGame()) {

                System.out.println("\n\nJogo carregado com sucesso.");
                startGame();

            }
            else {
                System.out.println("\n\nNão foi possível carregar o jogo.");
            }

        }        

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

        if(this.game.inAwaitBeginning()){
            
            AwaitBeginningMenu();

            String name = new String();
            name = scanString("Insira o nome do Jogador: ");

            this.game.newUser(name);
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

        if (this.game.inAwaitCrewMembersSelection()) {
            
            int i;
            
            for (i = 0; i < 2; i++) {
                
                option[i] = CrewMemberSelectionMenu();

                if (option[i] == 0) {
                    
                    if (i == 0) {

                        this.game.newGame();
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

        if (this.game.inAwaitThirdTokenFirstCrewMember()) {
            
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

        if (this.game.inAwaitThirdTokenSecondCrewMember()) {
            
            int room = CrewMember2RoomSelectionMenu();

            if(room != 0)
                this.game.placeSecondCrewMember(room);            
            else
                this.game.goBack();

        }

    }

    public String chooseParticleDisperserMenu(){

        //TESTAR

        int option = -1;
        int i = 0;

        while (option < 0 || option > this.game.getNumberOfParticleDisperser()) {

            i = 0;
            clearConsole();
            System.out.println(MenuConstants.ChooseParticleDisperserConst);
            System.out.println(this.game.toStringParticleDisperser());
            System.out.println("0 - Voltar");
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        if(option == 0)
            return "Sair";
        else{
            option = option - 1;
            return this.game.getRoomNameOfParticleDisperser(option);
        }
            
    }

    /**
     * This function is the UI for the AwaitChooseParticleDisperser state
     */
    public void ui_AwaitChooseParticleDisperser(){

        if (this.game.inAwaitChooseParticleDisperser()) {
            
            String option = chooseParticleDisperserMenu();

            switch (option) {

                case "Sair":
                    
                    this.game.goBack();

                    break;
            
                default:

                    this.game.detonateParticleDisperser(option);
                    
                    System.out.println("------------------------");
                    System.out.println("Detonate praticle com o numero: " +option + " detonada" );
                    System.out.println("------------------------");
                    
                    break;
                    
            }

        }

    }

    public void ui_AwaitDiceRolling(){

        if(this.game.inAwaitDiceRolling()){

            clearConsole();
            int crewMemberIndex = SelectCrewMemberMenu(); 

            if (crewMemberIndex != 0){
                
                ArrayList<Integer> output = new ArrayList<Integer>();
                
                output = this.game.atack(crewMemberIndex - 1);
                
                if(output.get(2) != -1){
                    System.out.println("---------------------------------");
                    System.out.println("O resultado do dado foi:" + output.get(0)/output.get(1) + "com o dado lancado " + output.get(1) + " vezes e o player " + output.get(2));
                    System.out.println("---------------------------------");
                }
                else{
                    System.out.println("---------------------------------");
                    System.out.println("Não existe nenhum alien na sala");
                    System.out.println("---------------------------------");
                }
            }
            else{
                this.game.goBack();
            
            
            }

        }

    }

    public void ui_AwaitSaveGame(){

        if(this.game.wasSaved()){
            this.game.goBack();
        }
        
        if (this.game.inAwaitSaveGame()) {
            
            String option = " ";
              
            option = scanString("Deseja mesmo guardar (Y or N)?");

            if (option.toLowerCase().equals("y"))
                this.game.SaveGame();
                        
        }

    }

    public int MenuRestPhase(){
    
        int option = -1;

        while (option < 0 || option > 8) {
            
            clearConsole();
            System.out.println("Encontra-se na RestPhase. \nO que pretende fazer:\n");
            System.out.println("Inspiration Points (IP):" + this.game.getInspirationPoints());
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

        if (this.game.inAwaitRestPhaseActions()) {
            //interface
            
            int opcao = MenuRestPhase();
            
            if(opcao >=0 && opcao < 9){
                
                boolean outputs = false;
                
                switch(opcao){
                    
                    case 1:
                        
                        outputs = this.game.addonehealth();
                        ui_restphaseout(outputs);
                        break;

                    case 2:
                        
                        outputs = this.game.repairhull();
                        ui_restphaseout(outputs);
                        
                        break;    

                    case 3:
                        
                        outputs = this.game.buildorganicdetonator();
                        ui_restphaseout(outputs);
                        
                        break;

                    case 4:
                        
                        opcao = SelectCrewMemberMenu();
                        
                        if (opcao != 0){
                            outputs = this.game.addonemovement(opcao - 1);
                            ui_restphaseout(outputs);
                        
                        }
                        break;

                    case 5:
                        
                        outputs = this.game.builoneparticleddispenser();
                        ui_restphaseout(outputs);
                        
                        break; 

                    case 6:
                        
                        outputs = this.game.gainonesealedromtoken();
                        ui_restphaseout(outputs);
                        
                        break;   

                    case 7:
                        
                        opcao = SelectCrewMemberMenu();
                        
                        if (opcao != 0)                        
                            outputs = this.game.gainoneattack(opcao);
                            ui_restphaseout(outputs);
                        
                        break; 

                    case 8:
                        
                        outputs = this.game.addonethedice();
                        ui_restphaseout(outputs);
                        
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

            //clearConsole();
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

        while(!options.contains(option) && option != 0){

            clearConsole();
            System.out.println(MenuConstants.beginningConst + this.game.toStringAvailableCrewMembers(options));
            System.out.println("0 - Voltar");
            option = scanInteger("\nInsira a opcao que pretende: ");

        }
        
        return option;

    }

    public void showStatus(){
        System.out.println(this.game.showStatus());
    }

    /**
     * This function does all the Crew Phase Actions
     */
    public void ui_AwaitCrewPhaseActions(){

        if (this.game.inAwaitCrewPhaseActions()) {
            
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

                    ui_heal(this.game.heal());
                    
                    break;

                //If Fix One Hull
                case 4:

                        ui_hull(this.game.fixOneHull());
                        
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

                    int redshirtoutput = this.game.redShirtSpecial();
                    ui_redshirt(redshirtoutput);
                    
                    break;

                //If is exit
                case 0:

                    this.game.goBack();

                    break;

            }

        }

    }

    public int SelectRoomToMove(int index){

        int option = -1,correu=0;        
        ArrayList<Integer> options = this.game.getNearAvailableRooms(index);
        String MenuRooms = this.game.getRoomsNear(index);

        while (!options.contains(option) && option != 0) {
            
            if(correu>0)
                System.out.println("Opção errada");
            
            clearConsole();
            System.out.println(MenuRooms);
            System.out.println("0 - Voltar\n");
            option = scanInteger("\nInsira a opcao que pretende: ");

            correu++;

        }

        return option;

    }
    
    public int SelectRoomToMoveSpecial(int index){
        
        int option = -1,allcorrect = 1;
        clearConsole();
        System.out.println("Foi escolhido um Transporter Cheif, logo pode escolher qualquer local:");
        
        System.out.println(this.game.showAllRooms());
        
        do{

            while (option <= 0 || option >12){
                option = scanInteger("\nInsira a opcao que pretende: ");
            }

            if (this.game.seelockedroom(option)){
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

        if (this.game.inAwaitPeekRoom()) {
            
            int index = SelectCrewMemberMenu();

            if(index != 0){
                
                if(this.game.isAnTransporterChief(index - 1)){
                    
                    int option = SelectRoomToMoveSpecial(index - 1);

                    if (option != 0) 
                        this.game.move(index - 1, option);
                    else
                        this.game.goBack();
                    
                }
                else{

                    int option = SelectRoomToMove(index - 1);
                    
                    if (option != 0){ 
                        
                        
                        this.game.move(index - 1, option);
                        
                        System.out.println("Crew memeber mudou de sala com sucesso");
                        
                    }
                    else{
                        this.game.goBack();
                    }             
                
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

        while(!options.contains(option) && option != 0){

            clearConsole();
            System.out.println(this.game.toStringRoomsToSeal());
            System.out.println("0 - Voltar\n");
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }

    /**
     * This function in to Seal a room
     */
    public void ui_SealRoom(){

        if (this.game.inAwaitPeekRoom()) {
            
            int option = SelectRoomToSeal();

            if (option != 0){
                this.game.sealRoom(option); 
                System.out.println("Sala selada com o numero: " + option);
            }
            else
                this.game.goBack();

        }
        else
            System.out.println("Não é possivel sela a sala (tip: verifica se tens sealed tokens)");

    }

    public int SelectTrap(){

        int option = -1;
        ArrayList<Integer> options = this.game.getAvailableTraps();

        while(!options.contains(option) && option != 0){

            clearConsole();
            System.out.println(this.game.toStringAvailableTraps());
            System.out.println("0 - Voltar\n");
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }    

    public int SelectTrapRoom(int trapIndex){

        int option = -1;
        ArrayList<Integer> options = this.game.getAvailableTrapRooms(trapIndex);

        while(!options.contains(option) && option != 0){

            clearConsole();
            System.out.println(this.game.toStringAvailableTrapRooms(trapIndex));
            System.out.println("0 - Voltar\n");
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }
    
    /**
     * This function in to set a trap
     */
    public void ui_SetTrap(){

        if (this.game.inAwaitPeekRoom()) {
            
            int trapIndex = SelectTrap();

            if (trapIndex != 0){
            
                int roomIndex = SelectTrapRoom(trapIndex);

                if (roomIndex != 0) {
                    
                    this.game.setTrap(roomIndex, trapIndex);
                    
                    System.out.println("Trap colocada com sucesso");
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

        System.out.println("\n\nentrei");

        if (this.game.inAwaitPeekRoom()) {

            System.out.println("\n\nentrei2");

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

            //clearConsole();
            System.out.println(MenuConstants.GameOverConst);
            option = scanInteger("\nInsira a opcao que pretende: ");

        }

        return option;

    }

    /**
     * This function ends the game
     */
    public void ui_GameOver(){

        if (this.game.inGameOverState()) {

            if (this.game.SaveScore()){
                System.out.println("\n\nNão foi possivel guardar a pontuação do jogo.");
            }

            int option;
            System.out.println( "==== Game Over =====\n");

            // Player score
            System.out.println("---Pontuação---");

            System.out.println(this.game.getUserPontuation());

            option = MenuGameOver();

            if (option == 1){

                this.game.newGame();

            }

        }

    }

    /**
     * This function clears the console
     */
    public void clearConsole() {

        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }

        if (System.getProperties().getProperty("os.name").contains("Windows")) {

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                //e.printStackTrace();
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

        //while not gameOver
        while(!(this.game.inGameOverState())){

            if (this.game.inAwaitBeginning())
                ui_AwaitBeginning();

            if(this.game.inAwaitCrewMembersSelection())
                ui_AwaitCrewMemberSelection();

            if (this.game.inAwaitThirdTokenFirstCrewMember())
                ui_AwaitThirdTokenFirstCrewMember();

            if (this.game.inAwaitThirdTokenSecondCrewMember())
                ui_AwaitThirdTokenSecondCrewMember();

            if (this.game.inAwaitChooseParticleDisperser())
                ui_AwaitChooseParticleDisperser();

            if (this.game.inAwaitDiceRolling())
                ui_AwaitDiceRolling();

            if (this.game.inAwaitSaveGame())
                ui_AwaitSaveGame();

            if (this.game.inAwaitRestPhaseActions())
                ui_AwaitRestPhaseActions();

            if(this.game.inAwaitCrewPhaseActions())
                ui_AwaitCrewPhaseActions();

            if (this.game.inGameOverState())
                ui_GameOver();

        }

        this.game.newGame();
        
    }

    private void extraSettings() {

        System.out.println("1- modifica o JourneyTracker");
        System.out.println("2- modifica o HealthTracker");
        System.out.println("3- modifica o Hulltracker");
        System.out.println("Outro numero para sair");

        int choice = scanInteger("Que opcao pretende: ");

        int pos;

        switch (choice){

            case 1:

                do{
                    pos = scanInteger("Que posição pretende mudar?");
                }while(pos <= 0 || pos > 13);

                String info;

                do {
                    info = scanString("Que String prende meter nessa posição?");
                } while (this.game.validateJourneyTrackerOption(info));

                if (!this.game.changeJourney(pos, info.toUpperCase()))
                    System.out.println("Não foi possivel modificar o Journey Tracker");

                break;
            case 2:

                pos = scanInteger("Qual o valor que pretende introduzir?");
                this.game.setHealthTrackerValue(pos);

                break;
            case 3:

                pos = scanInteger("Qual o valor que pretende introduzir?");
                this.game.setHullTrackerValue(pos);

                break;

        }
        
    }

    private void mostrapont() {
            clearConsole();
            System.out.println("---Pontuação---");
            System.out.println(this.game.getUserPontuation());
            String opt = scanString("\nPressione Enter para continuar...");

    }

    private void ui_heal(boolean helthoutput) {
        clearConsole();
        if(helthoutput == false){
            System.out.println("-------------------------");
            System.out.println("Não é possivel aumentar a vida");
            System.out.println("-------------------------");
        }
        else{
            System.out.println("-------------------------");
            System.out.println("Vida aumentada com sucesso");
            System.out.println("-------------------------");
        }
        
    }

    private void ui_hull(boolean hulloutput) {
        clearConsole();
        if(hulloutput == false){
            System.out.println("-------------------------");
            System.out.println("Não é possivel aumentar o hull");
            System.out.println("-------------------------");
        }
        else{
            System.out.println("-------------------------");
            System.out.println("Hull aumentado com sucesso");
            System.out.println("-------------------------");
        }
    }

    private void ui_redshirt(int redshirtoutput) {
        
        clearConsole();
        System.out.println("-------------------------");
        
        if(redshirtoutput == 0){
            
            System.out.println("Um redshirt foi abdicado");
            
        }
        if(redshirtoutput == 1){
            ;
            System.out.println("Não existem redshirts");
            
        }
        if(redshirtoutput == 2){
            
            System.out.println("Os 2 redshirts foram eliminados");
            
        }

        System.out.println("-------------------------");
        
    }

    private void ui_restphaseout(boolean outputs) {
        clearConsole();
        if (outputs == true)
            System.out.println("Foi possivel realizar a opção escolhida");
        else
            System.out.println("Não foi possivel realizar a opção escolhida");
    }
    
}

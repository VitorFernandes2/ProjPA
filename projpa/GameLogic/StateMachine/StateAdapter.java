package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import java.util.ArrayList;
import projpa.GameLogic.GameData;

/**
 * StateAdapter
 */
public class StateAdapter implements IStates,Serializable{

    GameData game;

    public StateAdapter(GameData game) {

        this.game = game;

    }

    public StateAdapter() {
        
    }

    @Override
    public IStates setup(String name) {
        return this;
    }

    @Override
    public IStates selectCrewMembers(String crewMember1, String crewMember2) {
        return this;
    }

    @Override
    public IStates selectRandomCrewMembers() {
        return this;
    }

    @Override
    public IStates placeFirstCrewMember(int room) {
        return this;
    }

    @Override
    public IStates placeSecondCrewMember(int room) {
        return this;
    }

    @Override
    public IStates atack(int crewMemberIndex) {
        return this;
    }

    @Override
    public IStates saveGame() {
        
        return this;
    }

    @Override
    public Boolean heal() {
        return false;
    }

    @Override
    public Boolean fixOneHull() {
        return false;
    }

    @Override
    public IStates setTrap(int room, int trapIndex) {
        return this;
    }

    @Override
    public IStates InputBeginning(GameData game) {
        return this;
    }    

    @Override
    public IStates StartNewBegin() {
        return this;
    }

    @Override
    public IStates goBack() {
        return this;
    }

    @Override
    public IStates detonateParticleDisperser(String particleDisperser) {
        return this;
    }

    @Override
    public IStates nextTurn() {
        return this;    
    }

    @Override
    public IStates sealRoom(int roomIndex) {
        return this;
    }

    @Override
    public IStates move(int crewMemberIndex, int crewMemberNewRoom) {
        return this;
    }
    
    @Override
    public boolean addonehealth(){
        return false;
    }
    @Override
    public boolean repairhull(){
        return false;
    }
    @Override
    public boolean buildorganicdetonator(){
        return false;
    }
    @Override
    public boolean builoneparticleddispenser(){
        return false;
    }
    @Override
    public boolean addonemovement(int crewmemeber){
        return false;
    }
    @Override
    public boolean gainonesealedromtoken(){
        return false;
    }
    @Override
    public boolean gainoneattack(int crewmember){
        return false;
    }
    @Override
    public boolean addonethedice(){
        return false;
    }
    @Override
    public Boolean wasSaved(){
        return false;
    }
    
    @Override
    public ArrayList<Integer> getgeninfo(){
        ArrayList<Integer> returns = new ArrayList<Integer>();
        returns.add(-1);
        return returns;
    }
   
}
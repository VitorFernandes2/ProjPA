package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * StateAdapter
 */
public class StateAdapter implements IStates,Serializable{

    GameLogic game;

    public StateAdapter(GameLogic game) {

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
    public IStates selectrandomCrewMembers() {
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
    public IStates heal() {
        return this;
    }

    @Override
    public IStates fixOneHull() {
        return this;
    }

    @Override
    public IStates setTrap(int room, int trapIndex) {
        return this;
    }

    @Override
    public IStates alienPhase(GameLogic game) {
        return this;
    }

    @Override
    public IStates Inputbegining(GameLogic game) {
        return this;
    }    

    @Override
    public IStates Startnewbegin() {
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
    public void addonehealth(){
    }
    @Override
    public void repairhull(){
        
    }
    @Override
    public void buildorganicdetonator(){
        
    }
    @Override
    public void builoneparticleddispenser(){
        
    }
    @Override
    public void addonemovement(int crewmemeber){
        
    }
    @Override
    public void gainonesealedromtoken(){
        
    }
    @Override
    public void gainoneattack(int crewmember){
        
    }
    @Override
    public void addonethedice(){
        
    }
    @Override
    public Boolean wassaved(){  
        return false;
    }
   
}
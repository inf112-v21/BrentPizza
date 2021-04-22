package inf112.skeleton.app.GUI.HUD;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GameLogic.HudLogic;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IHudLogic;

public class Hud {

    private Stage stage;
    private Viewport viewport;
    ProgramTableGenerator programCardsTable;
    HandTableGenerator handCardsTable;
    IHudLogic hudLogic;
    HudStatsGenerators hudGen;

    public Hud(SpriteBatch sb, IBoardLogic boardLogic){
        viewport = new FitViewport(1920, 1080, new OrthographicCamera());

        stage = new Stage(viewport, sb);

        hudLogic = new HudLogic(boardLogic, this);

        programCardsTable = new ProgramTableGenerator();
        handCardsTable = new HandTableGenerator();
        hudGen = new HudStatsGenerators();

        stage.addActor(programCardsTable.createCardTable(hudLogic.getProgramImageList(), hudLogic.getReadyButton()));
        stage.addActor(handCardsTable.createHandTable(hudLogic.getHandButtonList(), hudLogic.getHandPriority()));
        stage.addActor(hudGen.HudStatsGenerators(boardLogic));
    }

    public Stage getStage(){
        return this.stage;
    }

    public void updateStage(IBoardLogic boardLogic){
        stage.clear();
        stage.addActor(hudGen.HudStatsGenerators(boardLogic));
        stage.addActor(programCardsTable.createCardTable(hudLogic.getProgramImageList(), hudLogic.getReadyButton()));
        stage.addActor(handCardsTable.createHandTable(hudLogic.getHandButtonList(), hudLogic.getHandPriority()));
    }


}

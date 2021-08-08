package alt.sim.controller.game;

import alt.sim.model.game.Game;
import alt.sim.model.plane.Plane;
import alt.sim.model.plane.State;
import alt.sim.view.seaside.Seaside;
import javafx.animation.Animation;

import java.util.List;

public final class GameController {

    private static final int LIMIT_500 = 500;
    private static final int LIMIT_1000 = 1000;
    private static final int LIMIT_1500 = 1500;
    private static final int LIMIT_2000 = 2000;
    private static final int LIMIT_2100 = 2100;

    private static Seaside transitionSeaside;
    private static Game gameModel;

    public GameController(final Seaside seaside, final Game game) {
        transitionSeaside = seaside;
        gameModel = game;
    }


    private static void pauseResumeOrStop(final boolean pause, final boolean resume, final boolean stop) {
        //TODO sostituzione con gameModel
        //List<Plane> planes = transitionSeaside.getPlanes();
        List<Plane> planes = gameModel.getPlanes();

        planes.forEach(plane -> {
            if (pause) {
                if (plane.getPlaneMovementAnimation() != null) {
                    plane.getPlaneMovementAnimation().pause();
                }
                if (plane.getLandingAnimation() != null) {
                    plane.getLandingAnimation().pause();
                }
                if (plane.getRandomTransition() != null) {
                    plane.getRandomTransition().pause();
                }
                transitionSeaside.getSpawnCountDown().pause();
                Plane.getPathTransitionList().forEach(Animation::pause);
            } else if (resume) {
                if (plane.getPlaneMovementAnimation() != null && plane.getStatusMovementAnimation().equals("PAUSED")) {
                    plane.getPlaneMovementAnimation().play();
                }
                if (plane.getLandingAnimation() != null && plane.getStatusMovementAnimation().equals("PAUSED")) {
                    plane.getLandingAnimation().play();
                }
                if (plane.getRandomTransition() != null && plane.getStatusMovementAnimation().equals("PAUSED")) {
                    plane.getRandomTransition().play();
                }
                transitionSeaside.getSpawnCountDown().play();
            } else if (stop) {
                if (plane.getPlaneMovementAnimation() != null) {
                    plane.getPlaneMovementAnimation().stop();
                }
                if (plane.getLandingAnimation() != null) {
                    plane.getLandingAnimation().stop();
                }
                if (plane.getRandomTransition() != null) {
                    plane.getRandomTransition().stop();
                }

                plane.setState(State.TERMINATED);
                plane.terminateAllAnimation();
                transitionSeaside.getSpawnCountDown().stop();
                Plane.getPathTransitionList().forEach(Animation::stop);
            }
        });
    }

    public static void pause() {
        pauseResumeOrStop(true, false, false);
    }

    public static void resume() {
        pauseResumeOrStop(false, true, false);
    }

    public static void stop() {
        pauseResumeOrStop(false, false, true);
    }

    public void checkScore(final int score) {
         if (score < LIMIT_2100) {
            if (score >= LIMIT_500 && score < LIMIT_1000) {
                transitionSeaside.setNumberPlanesToSpawn(2);
            } else if (score >= LIMIT_1000 && score <= LIMIT_1500) {
                transitionSeaside.setNumberPlanesToSpawn(3);
            } else if (score >= LIMIT_2000) {
                transitionSeaside.setNumberPlanesToSpawn(4);
            }
        }
    }
}

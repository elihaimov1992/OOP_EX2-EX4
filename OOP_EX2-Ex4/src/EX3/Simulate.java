package EX3;

import java.util.Iterator;

/**
 * This class represents an thread that continuously moves the packmans
 */
class Simulate implements Runnable{

		Game game;
		boolean running = true;
		
		public Simulate(Game game) {
			this.game = game;
		}
		
		@Override
		public void run() {
				while(running) {
					Iterator<Packman> it = game.getPackmanArrayList().iterator();
					while (it.hasNext()) {
						Packman curr_pack = it.next();
						Fruit destination = curr_pack.path.points.get(curr_pack.dest_id);
						curr_pack.moveInDirection(destination, 0);
					}
				}
		}

	}


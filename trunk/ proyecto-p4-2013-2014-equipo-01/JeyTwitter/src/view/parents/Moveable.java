package view.parents;

import java.awt.Point;

public interface Moveable {

	public Point getInitialClick();

	public void setInitialClick(Point initialClick);

	public Point getLastPosition();

	public void setLastPosition(Point lastPosition);
}

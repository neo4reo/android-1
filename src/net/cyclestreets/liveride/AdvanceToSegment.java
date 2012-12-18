package net.cyclestreets.liveride;

import net.cyclestreets.api.Journey;
import net.cyclestreets.api.Segment;

import org.osmdroid.util.GeoPoint;

final class AdvanceToSegment extends LiveRideState
{
  AdvanceToSegment(final LiveRideState previous,
                   final Journey journey,
                   final Segment segment) 
  {
    super(previous);
    journey.setActiveSegment(segment);
    notify(segment);
  }
  
  @Override
  public LiveRideState update(Journey journey, GeoPoint whereIam)
  {
    if(journey.atWaypoint())
      return new PassingWaypoint(this);
    if(journey.atEnd())
      return new Arrivee(this);
    
    return new OnTheMove(this);
  } // update

  @Override
  public boolean isStopped() { return false; }
  @Override
  public boolean arePedalling() { return true; }
}

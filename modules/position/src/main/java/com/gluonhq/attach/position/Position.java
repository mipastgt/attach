/*
 * Copyright (c) 2016, 2019 Gluon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL GLUON BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gluonhq.attach.position;

/**
 * A class that contains the latitude and longitude coordinates that map to a
 * specific point location on earth.
 *
 * @since 3.0.0
 */
public final class Position {

    private final long utcTimeMillis;
    private final double latitude;
    private final double longitude;
    private final double altitude;
    private final double speed;
    private final double bearing;
    private final double accuracy;

    /**
     * Construct a new position with the specified <code>utcTimeMillis</code>, <code>latitude</code> and
     * <code>longitude</code>. It sets the <code>altitude</code> as NaN.
     *
     * @param utcTimeMillis the UTC timestamp of the new position in milliseconds since January 1, 1970.
     * @param latitude the latitude coordinate of the new position
     * @param longitude the longitude coordinate of the new position
     */
    public Position(long utcTimeMillis, double latitude, double longitude) {
        this(utcTimeMillis, latitude, longitude, Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

    /**
     * Construct a new position with the specified <code>latitude</code>,
     * <code>longitude</code>, <code>altitude</code>, <code>speed</code>, <code>bearing</code> and <code>accuracy</code>.
     *
     * @param utcTimeMillis the UTC timestamp of the new position in milliseconds since January 1, 1970.
     * @param latitude the latitude coordinate of the new position
     * @param longitude the longitude coordinate of the new position
     * @param altitude the altitude of the new position
     * @param speed the speed of the new position
     * @param bearing the bearing of the new position
     * @param accuracy the horizontal accuracy of the new position
     */
    public Position(long utcTimeMillis, double latitude, double longitude, double altitude, double speed, double bearing, double accuracy) {
        this.utcTimeMillis = utcTimeMillis;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
        this.bearing = bearing;
        this.accuracy = accuracy;
    }

    /**
     * Return the UTC timestamp of the position in milliseconds since January 1, 1970.
     *
     * @return a double representing the latitude coordinate
     */
    public final long getUtcTimeMillis() {
        return this.utcTimeMillis;
    }

    /**
     * Return the latitude coordinate of the position.
     *
     * @return a double representing the latitude coordinate
     */
    public final double getLatitude() {
        return this.latitude;
    }

    /**
     * Return the longitude coordinate of the position.
     *
     * @return a double representing the longitude coordinate
     */
    public final double getLongitude() {
        return this.longitude;
    }

    /**
     * Return the altitude of the position, above mean sea level, in meters.
     *
     * @return a double representing the altitude in meters or NaN if no value is available
     */
    public final double getAltitude() {
        return altitude;
    }

    /**
     * Return the speed in meters per second.
     *
     * @return a double representing the speed in meters per second or NaN if no value is available
     */
    public final double getSpeed() {
        return speed;
    }

    /**
     * Return the bearing in degrees.
     *
     * @return a double representing the bearing in degrees or NaN if no value is available
     */
    public final double getBearing() {
        return bearing;
    }

    /**
     * Return the horizontal accuracy of the position in meters.
     *
     * @return a double representing the horizontal accuracy of the position in meters or NaN if no value is available
     */
    public final double getAccuracy() {
        return accuracy;
    }

	/**
	 * Return whether we have an altitude value.
	 *
	 * @return a boolean which indicates whether we have an altitude value.
	 */
	public final boolean hasAltitude() {
		return !Double.isNaN(altitude);
	}

	/**
	 * Return whether we have a speed value.
	 *
	 * @return a boolean which indicates whether we have a speed value.
	 */
	public final boolean hasSpeed() {
		return !Double.isNaN(speed);
	}

	/**
	 * Return whether we have a bearing value.
	 *
	 * @return a boolean which indicates whether we have a bearing value.
	 */
	public final boolean hasBearing() {
		return !Double.isNaN(bearing);
	}

	/**
	 * Return whether we have a horizontal accuracy value.
	 *
	 * @return a boolean which indicates whether we have a horizontal accuracy value.
	 */
	public final boolean hasAccuracy() {
		return !Double.isNaN(accuracy);
	}

    @Override
    public String toString() {
        return "Position{" + "utcTimeMillis=" + utcTimeMillis + ", latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + '}';
    }

}

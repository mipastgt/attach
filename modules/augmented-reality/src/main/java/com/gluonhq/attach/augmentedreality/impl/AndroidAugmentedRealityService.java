/*
 * Copyright (c) 2018, 2020, Gluon
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
package com.gluonhq.attach.augmentedreality.impl;

import com.gluonhq.attach.augmentedreality.ARModel;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AndroidAugmentedRealityService extends DefaultAugmentedRealityService {

    private static final Logger LOG = Logger.getLogger(AndroidAugmentedRealityService.class.getName());

    private static final ReadOnlyBooleanWrapper CANCELLED = new ReadOnlyBooleanWrapper();
    private static final ReadOnlyObjectWrapper<Availability> AR_AVAILABILITY = new ReadOnlyObjectWrapper<>();

    static {
        System.loadLibrary("augmentedreality");
    }

    public AndroidAugmentedRealityService() {
    }

    @Override
    public Availability getAvailability() {
        AR_AVAILABILITY.set(Availability.valueOf(checkAR()));
        return AR_AVAILABILITY.get();
    }

    @Override
    public ReadOnlyObjectProperty<Availability> availabilityProperty() {
        return AR_AVAILABILITY.getReadOnlyProperty();
    }

    @Override
    public void setModel(ARModel model) {
        String obj = model.getObjFilename();
        if (obj != null) {
            getFileFromAssets(obj.replace(".obj",".mtl"));
            obj = getFileFromAssets(obj).toString();
        }
        String txt = model.getTextureFile();
        if (txt != null) {
            txt = getFileFromAssets(txt).toString();
        }
        setARModel(obj, txt, model.getScale());
    }

    @Override
    public void showAR() {
        if (debug) LOG.log(Level.INFO, "Show AR...");
        CANCELLED.setValue(false);
        showNativeAR();
    }

    @Override
    public void debugAR(boolean enable) {
        enableDebugAR(enable);
    }

    @Override
    public ReadOnlyBooleanProperty cancelled() {
        return CANCELLED.getReadOnlyProperty();
    }

    // native
    private static native String checkAR();
    private native void showNativeAR();
    private native void setARModel(String objFilename, String textureFile, double scale);

    private static native void enableDebugAR(boolean enable);

    // callback
    private static void notifyAvailability(String value) {
        Availability av = Availability.valueOf(value);
        if (AR_AVAILABILITY.get() != av) {
            Platform.runLater(() -> AR_AVAILABILITY.set(av));
        }
    }

    private static void notifyCancel() {
        if (!CANCELLED.get()) {
            Platform.runLater(() -> CANCELLED.set(true));
        }
    }

}

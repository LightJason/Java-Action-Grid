/*
 * @cond LICENSE
 * ######################################################################################
 * # LGPL License                                                                       #
 * #                                                                                    #
 * # This file is part of the LightJason                                                #
 * # Copyright (c) 2015-19, LightJason (info@lightjason.org)                            #
 * # This program is free software: you can redistribute it and/or modify               #
 * # it under the terms of the GNU Lesser General Public License as                     #
 * # published by the Free Software Foundation, either version 3 of the                 #
 * # License, or (at your option) any later version.                                    #
 * #                                                                                    #
 * # This program is distributed in the hope that it will be useful,                    #
 * # but WITHOUT ANY WARRANTY; without even the implied warranty of                     #
 * # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                      #
 * # GNU Lesser General Public License for more details.                                #
 * #                                                                                    #
 * # You should have received a copy of the GNU Lesser General Public License           #
 * # along with this program. If not, see http://www.gnu.org/licenses/                  #
 * ######################################################################################
 * @endcond
 */

package org.lightjason.agentspeak.action.grid;

import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lightjason.agentspeak.action.grid.routing.EDistance;
import org.lightjason.agentspeak.testing.IBaseTest;


/**
 * test background algorithms
 */
public final class TestCComponents extends IBaseTest
{

    /**
     * test angle
     */
    @Test
    public void angle()
    {
        Assertions.assertEquals(
            90D,
            Math.toDegrees( CCommon.angle( new DenseDoubleMatrix1D( new double[]{0, 1} ), new DenseDoubleMatrix1D( new double[]{1, 0} ) ).getKey() ),
            0
        );

        Assertions.assertFalse(
            CCommon.angle( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{0, 0} ) ).getValue()
        );
    }

    /**
     * test rotation matrix
     */
    @Test
    public void rotation()
    {
        Assertions.assertArrayEquals(
            new double[]{0, -1, 1, 0},
            CCommon.rotationmatrix2d( Math.toRadians( 90 ) ).vectorize().toArray(),
            0.0001
        );

        Assertions.assertArrayEquals(
            new double[]{0, 1, -1, 0},
            CCommon.rotationmatrix2d( Math.toRadians( 270 ) ).vectorize().toArray(),
            0.0001
        );
    }

    /**
     * test distance calculation
     */
    @Test
    public void distancecalculation()
    {
        Assertions.assertEquals(
            0D,
            CCommon.euclidandistance( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{0, 0} ) ),
            0
        );

        Assertions.assertEquals(
            2.8284271247461903,
            CCommon.euclidandistance( new DenseDoubleMatrix1D( new double[]{2, 0} ), new DenseDoubleMatrix1D( new double[]{0, 2} ) ),
            0.00001
        );
    }

    /**
     * test movement
     */
    @Test
    public void movement()
    {
        Assertions.assertArrayEquals(
            new double[]{0, 1},
            EMovementDirection.FORWARD
                    .position(
                            new DenseDoubleMatrix1D( new double[]{0, 0} ),
                            new DenseDoubleMatrix1D( new double[]{0, 1} )
                    ).assign( Math::round ).toArray(),
            0
        );

        Assertions.assertArrayEquals(
            new double[]{0, -1},
            EMovementDirection.BACKWARD
                    .position(
                            new DenseDoubleMatrix1D( new double[]{0, 0} ),
                            new DenseDoubleMatrix1D( new double[]{0, 1} )
                    ).assign( Math::round ).toArray(),
            0
        );

        Assertions.assertArrayEquals(
            new double[]{1, 0},
            EMovementDirection.LEFT
                    .position(
                            new DenseDoubleMatrix1D( new double[]{0, 0} ),
                            new DenseDoubleMatrix1D( new double[]{0, 1} )
                    ).assign( Math::round ).toArray(),
            0
        );

        Assertions.assertArrayEquals(
            new double[]{-1, 0},
            EMovementDirection.RIGHT
                    .position(
                            new DenseDoubleMatrix1D( new double[]{0, 0} ),
                            new DenseDoubleMatrix1D( new double[]{0, 1} )
                    ).assign( Math::round ).toArray(),
            0
        );
    }

    /**
     * test distance
     */
    @Test
    public void distance()
    {

        Assertions.assertEquals(
            18D,
            EDistance.MANHATTAN.apply( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{8, 10} ) )
        );

        Assertions.assertEquals(
            12.806248474865697,
            EDistance.EUCLIDEAN.apply( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{8, 10} ) )
        );

        Assertions.assertEquals(
            10D,
            EDistance.CHEBYSHEV.apply( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{8, 10} ) )
        );

        Assertions.assertEquals(
            13.313708498984761,
            EDistance.OCTILE.apply( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{8, 10} ) )
        );
    }

    /**
     * test heuristic
     */
    @Test
    public void heuristic()
    {
        Assertions.assertEquals(
            18D,
            EDistance.MANHATTAN.heuristic( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{8, 10} ) )
        );

        Assertions.assertEquals(
            18D,
            EDistance.EUCLIDEAN.heuristic( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{8, 10} ) )
        );

        Assertions.assertEquals(
            18D,
            EDistance.OCTILE.heuristic( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{8, 10} ) )
        );

        Assertions.assertEquals(
            18D,
            EDistance.OCTILE.heuristic( new DenseDoubleMatrix1D( new double[]{0, 0} ), new DenseDoubleMatrix1D( new double[]{8, 10} ) )
        );
    }

}

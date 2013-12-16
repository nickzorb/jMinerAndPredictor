/* 
 * Copyright (C) 2013 Harokopio University <Nikolaos Zormpas>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gr.hua.data_structures.basic;

/**
 *
 * @author NickZorb
 */
public class Measures {
    public static final String ROC_STATISTIC = "CS";
    public static final String SUCCESS_PERCENTAGE = "SR";
    public static final String KAPPA_STATISTIC = "KS";
    public static final String CORRELATION_COEFICIENT = "CC";
    public static final String INCORECTLY_CLASSIFIED_PERCENTAGE = "ICP";
    
    public static final String[] measures = {ROC_STATISTIC, SUCCESS_PERCENTAGE,
        KAPPA_STATISTIC, INCORECTLY_CLASSIFIED_PERCENTAGE};
}
